package com.cat.mid.demo.cart.cartms.domain.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryGoods {
    private String goodsId;
    private String goodsName;
    private Integer unitPrice;
    private Integer inventory;
}
