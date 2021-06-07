package com.cat.mid.demo.cart.cartms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_list")
@IdClass(CartListEntityPK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartListEntity {
    @Id
    @Column(name = "cart_id")
    private String cartId;
    @Id
    @Column(name = "goods_id")
    private String goodsId;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "goods_count")
    private int goodsCount;
    @Column(name = "goods_unit_price")
    private int goodsUnitPrice;
}
