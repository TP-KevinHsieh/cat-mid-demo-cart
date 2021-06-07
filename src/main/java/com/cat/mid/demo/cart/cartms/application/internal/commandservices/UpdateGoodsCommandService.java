package com.cat.mid.demo.cart.cartms.application.internal.commandservices;

import com.cat.mid.demo.cart.cartms.application.internal.queryservices.QueryGoodsService;
import com.cat.mid.demo.cart.cartms.domain.commands.UpdateGoodsInventoryCommand;
import com.cat.mid.demo.cart.cartms.domain.queries.QueryGoods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UpdateGoodsCommandService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QueryGoodsService queryGoodsService;

    @Value("${goods.service.api.url}")
    private String goodsServiceApiUrl;

    private final ObjectMapper mapper = new ObjectMapper();

    public Boolean updateGoodsInventory(String goodsId, Integer goodsCount) {
        QueryGoods queryGoods = queryGoodsService.queryGoodsByGoodsId(goodsId);

        try {
            if (queryGoods != null) {
                if (queryGoods.getInventory() <= 0) {
                    log.info("Goods:{} inventory not enough", goodsId);
                    return false;
                }

                String request = mapper.writeValueAsString(new UpdateGoodsInventoryCommand(queryGoods.getInventory() - goodsCount));

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> httpEntity = new HttpEntity<>(request, httpHeaders);

                restTemplate.patchForObject(goodsServiceApiUrl + "/" + goodsId + "/inventory", httpEntity, String.class);

                return true;
            } else {
                log.info("Couldn't found goods: {}", goodsId);
                return false;
            }
        } catch (JsonProcessingException e) {
            log.error("Update goods inventory json processing error", e);
        }

        return false;
    }
}
