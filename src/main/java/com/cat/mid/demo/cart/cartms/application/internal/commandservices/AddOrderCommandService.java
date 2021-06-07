package com.cat.mid.demo.cart.cartms.application.internal.commandservices;

import com.cat.mid.demo.cart.cartms.domain.commands.AddOrderCommand;
import com.cat.mid.demo.cart.cartms.domain.commands.AddOrderToServerCommand;
import com.cat.mid.demo.cart.cartms.domain.enums.StatusCode;
import com.cat.mid.demo.cart.cartms.domain.model.CartEntity;
import com.cat.mid.demo.cart.cartms.domain.model.CartListEntity;
import com.cat.mid.demo.cart.cartms.domain.model.CustomerEntity;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CartListRepositories;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CartRepositories;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CustomerRepositories;
import com.cat.mid.demo.cart.exceptions.CartException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Service
public class AddOrderCommandService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerRepositories customerRepositories;

    @Autowired
    private CartRepositories cartRepositories;

    @Autowired
    private CartListRepositories cartListRepositories;

    @Autowired
    private UpdateGoodsCommandService updateGoodsCommandService;

    @Value("${order.service.api.url}")
    private String orderServiceApiUrl;

    private final ObjectMapper mapper = new ObjectMapper();

    @Transactional(rollbackFor = {CartException.class, Exception.class})
    public AddOrderToServerCommand addOrder(AddOrderCommand addOrderCommand) {
        System.out.println(addOrderCommand.getCartId());
        Optional<CartEntity> cartEntity = cartRepositories.findById(addOrderCommand.getCartId());

        if (cartEntity.isPresent()) {
            Optional<CustomerEntity> customerEntity = customerRepositories.findById(cartEntity.get().getCustomerId());

            if (customerEntity.isPresent()) {
                List<CartListEntity> cartListEntityList = cartListRepositories.findAllByCartId(addOrderCommand.getCartId());

                if (CollectionUtils.isEmpty(cartListEntityList))
                    // Couldn't found any goods in cart
                    throw new CartException(StatusCode.C6001);

                cartListRepositories.deleteAllByCartId(addOrderCommand.getCartId());
                cartRepositories.deleteById(addOrderCommand.getCartId());

                Map<String, Integer> alreadyUpdatedGoodsInventoryMap = new HashMap<>();

                List<AddOrderToServerCommand.Goods> goodsList = new ArrayList<>();

                try {
                    cartListEntityList.forEach(x -> {
                        if (!updateGoodsCommandService.updateGoodsInventory(x.getGoodsId(), x.getGoodsCount()))
                            // Goods update inventory fail
                            throw new CartException(StatusCode.C6002);

                        alreadyUpdatedGoodsInventoryMap.put(x.getGoodsId(), x.getGoodsCount());

                        AddOrderToServerCommand.Goods goods = new AddOrderToServerCommand.Goods(
                                x.getGoodsCount(),
                                x.getGoodsId(),
                                x.getGoodsName(),
                                x.getGoodsUnitPrice()
                        );

                        goodsList.add(goods);
                    });

                    AddOrderToServerCommand addOrderToServerCommand = new AddOrderToServerCommand(
                            addOrderCommand.getCartId(),
                            customerEntity.get().getCustomerName(),
                            goodsList,
                            goodsList.stream()
                                    .map(AddOrderToServerCommand.Goods::getUnitPrice)
                                    .reduce(0, Integer::sum)
                    );

                    AddOrderToServerCommand result = addOrderToServer(addOrderToServerCommand);

                    if (result == null)
                        // Add order fail
                        throw new CartException(StatusCode.C8000);

                    return result;
                } catch (Exception e) {
                    log.error("Add order get error", e);

                    // Revert goods inventory
                    alreadyUpdatedGoodsInventoryMap
                            .forEach((x, y) -> updateGoodsCommandService.updateGoodsInventory(x, y));

                    // Add order fail
                    throw new CartException(StatusCode.C8000);
                }
            } else
                // Couldn't found customer
                throw new CartException(StatusCode.C5000);
        } else {
            // Couldn't found cart
            throw new CartException(StatusCode.C7000);
        }
    }

    public AddOrderToServerCommand addOrderToServer(AddOrderToServerCommand addOrderToServerCommand) {
        try {
            String request = mapper.writeValueAsString(addOrderToServerCommand);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>(request, httpHeaders);

            ResponseEntity<String> response = restTemplate.postForEntity(orderServiceApiUrl, httpEntity, String.class);

            if (response != null &&
                    response.getBody() != null &&
                    HttpStatus.CREATED == response.getStatusCode())
                return mapper.readValue(response.getBody(), AddOrderToServerCommand.class);
        } catch (JsonProcessingException e) {
            log.error("Add order json processing error", e);
        }

        return null;
    }
}