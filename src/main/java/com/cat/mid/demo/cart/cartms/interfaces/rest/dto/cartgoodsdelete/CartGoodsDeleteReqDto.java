package com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsdelete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartGoodsDeleteReqDto {
    @JsonProperty("cartId")
    private String cartId;
    @JsonProperty("goodsId")
    private String goodsId;
}
