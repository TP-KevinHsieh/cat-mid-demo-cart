package com.cat.mid.demo.cart.cartms.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGoodsInventoryCommand {
    private Integer inventory;
}
