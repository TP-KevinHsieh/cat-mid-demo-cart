package com.cat.mid.demo.cart.cartms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEntity {
    @Id
    @Column(name = "cart_id")
    private String cartId;
    @Column(name = "customer_id")
    private String customerId;
}