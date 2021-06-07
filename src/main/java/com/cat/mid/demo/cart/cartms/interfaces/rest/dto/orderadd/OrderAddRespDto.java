package com.cat.mid.demo.cart.cartms.interfaces.rest.dto.orderadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddRespDto {
    @JsonProperty("cartId")
    private String cartId;
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("goods")
    private List<Goods> goods;
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("totalAmount")
    private Integer totalAmount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Goods {
        @JsonProperty("count")
        private Integer count;
        @JsonProperty("goodsId")
        private String goodsId;
        @JsonProperty("goodsName")
        private String goodsName;
        @JsonProperty("unitPrice")
        private Integer unitPrice;
    }
}