package com.cat.mid.demo.cart.cartms.infrastructure.repositories;

import com.cat.mid.demo.cart.cartms.domain.model.CartListEntity;
import com.cat.mid.demo.cart.cartms.domain.model.CartListEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CartListRepositories extends JpaRepository<CartListEntity, CartListEntityPK>, JpaSpecificationExecutor<CartListEntity> {

    List<CartListEntity> findAllByCartId(String cartId);

    void deleteAllByCartId(String cartId);
}
