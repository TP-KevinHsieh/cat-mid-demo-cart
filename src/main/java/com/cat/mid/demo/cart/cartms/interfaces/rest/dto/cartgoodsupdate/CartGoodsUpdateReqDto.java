package com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsupdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartGoodsUpdateReqDto {
    @JsonProperty("cartId")
    private String cartId;
    @JsonProperty("goodsId")
    private String goodsId;
    @JsonProperty("newGoodsCount")
    private Integer newGoodsCount;
}
