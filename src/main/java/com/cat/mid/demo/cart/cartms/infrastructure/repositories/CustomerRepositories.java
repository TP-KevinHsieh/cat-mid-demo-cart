package com.cat.mid.demo.cart.cartms.infrastructure.repositories;

import com.cat.mid.demo.cart.cartms.domain.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepositories extends JpaRepository<CustomerEntity, String>, JpaSpecificationExecutor<CustomerEntity> {
}
