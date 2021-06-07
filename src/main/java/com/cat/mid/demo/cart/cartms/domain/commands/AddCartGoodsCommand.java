package com.cat.mid.demo.cart.cartms.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartGoodsCommand {
    private String cartId;
    private String goodsId;
    private Integer goodsCount;
}
