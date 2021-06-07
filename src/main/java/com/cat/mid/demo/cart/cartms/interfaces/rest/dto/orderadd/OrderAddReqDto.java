package com.cat.mid.demo.cart.cartms.interfaces.rest.dto.orderadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddReqDto {
    @JsonProperty("cartId")
    private String cartId;
}