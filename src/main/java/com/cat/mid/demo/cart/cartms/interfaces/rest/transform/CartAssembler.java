package com.cat.mid.demo.cart.cartms.interfaces.rest.transform;

import com.cat.mid.demo.cart.cartms.domain.commands.*;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartadd.CartAddReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartadd.CartAddRespDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsadd.CartGoodsAddReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsdelete.CartGoodsDeleteReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsupdate.CartGoodsUpdateReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.orderadd.OrderAddReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.orderadd.OrderAddRespDto;

import java.util.UUID;
import java.util.stream.Collectors;

public class CartAssembler {

    public static AddCartCommand toAddCartCommand(CartAddReqDto cartAddReqDto) {
        return new AddCartCommand(
                UUID.randomUUID().toString(),
                cartAddReqDto.getCustomerId()
        );
    }

    public static CartAddRespDto toCartAddRespDto(AddCartCommand cartCommand) {
        return new CartAddRespDto(
                cartCommand.getCartId()
        );
    }

    public static AddCartGoodsCommand toAddCartGoodsCommand(CartGoodsAddReqDto cartGoodsAddReqDto) {
        return new AddCartGoodsCommand(
                cartGoodsAddReqDto.getCartId(),
                cartGoodsAddReqDto.getGoodsId(),
                cartGoodsAddReqDto.getGoodsCount()
        );
    }

    public static UpdateCartGoodsCommand toUpdateCartGoodsCommand(CartGoodsUpdateReqDto cartGoodsUpdateReqDto) {
        return new UpdateCartGoodsCommand(
                cartGoodsUpdateReqDto.getCartId(),
                cartGoodsUpdateReqDto.getGoodsId(),
                cartGoodsUpdateReqDto.getNewGoodsCount()
        );
    }

    public static DeleteCartGoodsCommand toDeleteCartGoodsCommand(CartGoodsDeleteReqDto cartGoodsDeleteReqDto) {
        return new DeleteCartGoodsCommand(
                cartGoodsDeleteReqDto.getCartId(),
                cartGoodsDeleteReqDto.getGoodsId()
        );
    }

    public static AddOrderCommand toAddOrderCommand(OrderAddReqDto orderAddReqDto) {
        return new AddOrderCommand(
                orderAddReqDto.getCartId()
        );
    }

    public static OrderAddRespDto toOrderAddRespDto(AddOrderToServerCommand addOrderToServerCommand) {
        return new OrderAddRespDto(
                addOrderToServerCommand.getCartId(),
                addOrderToServerCommand.getCustomerName(),
                addOrderToServerCommand.getGoods()
                        .stream()
                        .map(x -> new OrderAddRespDto.Goods(
                                x.getCount(),
                                x.getGoodsId(),
                                x.getGoodsName(),
                                x.getUnitPrice()
                        ))
                        .collect(Collectors.toList()),
                addOrderToServerCommand.getOrderId(),
                addOrderToServerCommand.getTotalAmount()
        );
    }
}
