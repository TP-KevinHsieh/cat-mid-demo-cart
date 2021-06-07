package com.cat.mid.demo.cart.cartms.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartCommand {
    private String cartId;
    private String customerId;

    public AddCartCommand(String customerId) {
        this.customerId = customerId;
    }
}
