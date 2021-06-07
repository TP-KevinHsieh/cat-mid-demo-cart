package com.cat.mid.demo.cart.cartms.application.internal.commandservices;

import com.cat.mid.demo.cart.cartms.application.internal.queryservices.QueryGoodsService;
import com.cat.mid.demo.cart.cartms.domain.commands.UpdateCartGoodsCommand;
import com.cat.mid.demo.cart.cartms.domain.enums.StatusCode;
import com.cat.mid.demo.cart.cartms.domain.model.CartEntity;
import com.cat.mid.demo.cart.cartms.domain.model.CartListEntity;
import com.cat.mid.demo.cart.cartms.domain.model.CartListEntityPK;
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
public class UpdateCartGoodsCommandService {
    @Autowired
    private CartRepositories cartRepositories;

    @Autowired
    private CartListRepositories cartListRepositories;

    @Autowired
    private QueryGoodsService queryGoodsService;

    public void updateCartGoods(UpdateCartGoodsCommand updateCartGoodsCommand) {
        Optional<CartEntity> cartEntity = cartRepositories.findById(updateCartGoodsCommand.getCartId());

        if (cartEntity.isPresent()) {
            CartListEntityPK cartListEntityPK = new CartListEntityPK(
                    updateCartGoodsCommand.getCartId(),
                    updateCartGoodsCommand.getGoodsId()
            );

            Optional<CartListEntity> cartListEntity = cartListRepositories.findById(cartListEntityPK);

            if (cartListEntity.isPresent()) {
                QueryGoods queryGoods = queryGoodsService.queryGoodsByGoodsId(updateCartGoodsCommand.getGoodsId());

                if (queryGoods == null)
                    // Couldn't found goods
                    throw new CartException(StatusCode.C6001);

                if (queryGoods.getInventory() <= 0 ||
                        updateCartGoodsCommand.getNewGoodsCount() > queryGoods.getInventory())
                    // Goods count not enough
                    throw new CartException(StatusCode.C6000);

                cartListEntity.get().setGoodsCount(updateCartGoodsCommand.getNewGoodsCount());

                cartListRepositories.save(cartListEntity.get());
            } else
                // Couldn't found goods in db
                throw new CartException(StatusCode.C6001);
        } else
            // Couldn't found cart
            throw new CartException(StatusCode.C7000);
    }
}
