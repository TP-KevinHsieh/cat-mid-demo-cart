package com.cat.mid.demo.cart.cartms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "cart_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartListEntityPK implements Serializable {
    @Id
    @Column(name = "cart_id")
    private String cartId;
    @Id
    @Column(name = "goods_id")
    private String goodsId;
}
