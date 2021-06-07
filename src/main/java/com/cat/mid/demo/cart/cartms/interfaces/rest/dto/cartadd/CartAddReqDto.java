package com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartAddReqDto {
    @JsonProperty("customerId")
    private String customerId;
}
