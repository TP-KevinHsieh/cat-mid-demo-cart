package com.cat.mid.demo.cart.cartms.application.internal.commandservices;

import com.cat.mid.demo.cart.cartms.application.internal.queryservices.QueryGoodsService;
import com.cat.mid.demo.cart.cartms.domain.commands.AddCartGoodsCommand;
import com.cat.mid.demo.cart.cartms.domain.enums.StatusCode;
import com.cat.mid.demo.cart.cartms.domain.model.CartEntity;
import com.cat.mid.demo.cart.cartms.domain.model.CartListEntity;
import com.cat.mid.demo.cart.cartms.domain.queries.QueryGoods;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CartListRepositories;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CartRepositories;
import com.cat.mid.demo.cart.exceptions.CartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AddCartGoodsCommandService {
    @Autowired
    private CartRepositories cartRepositories;

    @Autowired
    private CartListRepositories cartListRepositories;

    @Autowired
    private QueryGoodsService queryGoodsService;

    public void addCartGoods(AddCartGoodsCommand addCartGoodsCommand) {
        Optional<CartEntity> cartEntity = cartRepositories.findById(addCartGoodsCommand.getCartId());

        if (cartEntity.isPresent()) {
            QueryGoods queryGoods = queryGoodsService.queryGoodsByGoodsId(addCartGoodsCommand.getGoodsId());

            if (queryGoods != null) {
                if (queryGoods.getInventory() <= 0 ||
                        addCartGoodsCommand.getGoodsCount() > queryGoods.getInventory())
                    // Goods count not enough
                    throw new CartException(StatusCode.C6000);

                CartListEntity cartListEntity = new CartListEntity(
                        addCartGoodsCommand.getCartId(),
                        addCartGoodsCommand.getGoodsId(),
                        queryGoods.getGoodsName(),
                        addCartGoodsCommand.getGoodsCount(),
                        queryGoods.getUnitPrice()
                );

                cartListRepositories.save(cartListEntity);
            } else
                // Could't found goods
                throw new CartException(StatusCode.C6001);
        } else
            // Could't found cart
            throw new CartException(StatusCode.C7000);
    }
}
