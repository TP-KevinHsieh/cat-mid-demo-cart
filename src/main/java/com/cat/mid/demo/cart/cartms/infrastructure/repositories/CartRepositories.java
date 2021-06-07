package com.cat.mid.demo.cart.cartms.infrastructure.repositories;

import com.cat.mid.demo.cart.cartms.domain.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepositories extends JpaRepository<CartEntity, String>, JpaSpecificationExecutor<CartEntity> {

    CartEntity findByCustomerId(String customerId);

}
