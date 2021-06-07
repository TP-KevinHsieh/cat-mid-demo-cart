package com.cat.mid.demo.cart.cartms.domain.commands;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddOrderToServerCommand {
    private String cartId;
    private String customerName;
    private List<Goods> goods;
    private String orderId;
    private Integer totalAmount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Goods {
        private Integer count;
        private String goodsId;
        private String goodsName;
        private Integer unitPrice;
    }

    public AddOrderToServerCommand(String cartId, String customerName, List<Goods> goods, Integer totalAmount) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.goods = goods;
        this.totalAmount = totalAmount;
    }
}
