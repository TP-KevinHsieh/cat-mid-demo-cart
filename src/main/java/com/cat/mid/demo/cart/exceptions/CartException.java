package com.cat.mid.demo.cart.exceptions;

import com.cat.mid.demo.cart.cartms.domain.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CartException extends RuntimeException {
    StatusCode statusCode;
}
