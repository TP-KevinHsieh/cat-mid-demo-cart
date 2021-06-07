package com.cat.mid.demo.cart.cartms.application.internal.commandservices;

import com.cat.mid.demo.cart.cartms.domain.commands.DeleteCartGoodsCommand;
import com.cat.mid.demo.cart.cartms.domain.enums.StatusCode;
import com.cat.mid.demo.cart.cartms.domain.model.CartListEntity;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CartListRepositories;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CartRepositories;
import com.cat.mid.demo.cart.exceptions.CartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DeleteCartGoodsCommandService {
    @Autowired
    private CartRepositories cartRepositories;

    @Autowired
    private CartListRepositories cartListRepositories;

    @Transactional
    public void deleteCartGoods(DeleteCartGoodsCommand deleteCartGoodsCommand) {
        List<CartListEntity> cartListEntityList = cartListRepositories.findAllByCartId(deleteCartGoodsCommand.getCartId());

        if (CollectionUtils.isEmpty(cartListEntityList))
            // Couldn't found any goods in cart
            throw new CartException(StatusCode.C6001);

        CartListEntity cartListEntity = cartListEntityList.stream()
                .filter(x -> deleteCartGoodsCommand.getGoodsId().equals(x.getGoodsId()))
                .findAny()
                // Couldn't found goods in db
                .orElseThrow(() -> new CartException(StatusCode.C6001));

        cartListRepositories.delete(cartListEntity);

        if (cartListEntityList.size() == 1)
            // The cart is empty
            cartRepositories.deleteById(cartListEntity.getCartId());
    }
}