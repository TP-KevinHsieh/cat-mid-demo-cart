package com.cat.mid.demo.cart.cartms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "customer_name")
    private String customerName;
}
