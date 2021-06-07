package com.cat.mid.demo.cart.cartms.application.internal.commandservices;

import com.cat.mid.demo.cart.cartms.domain.commands.AddCartCommand;
import com.cat.mid.demo.cart.cartms.domain.enums.StatusCode;
import com.cat.mid.demo.cart.cartms.domain.model.CartEntity;
import com.cat.mid.demo.cart.cartms.domain.model.CustomerEntity;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CartRepositories;
import com.cat.mid.demo.cart.cartms.infrastructure.repositories.CustomerRepositories;
import com.cat.mid.demo.cart.exceptions.CartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddCartCommandService {
    @Autowired
    private CartRepositories cartRepositories;

    @Autowired
    private CustomerRepositories customerRepositories;

    public AddCartCommand addCart(AddCartCommand addCartCommand) {
        Optional<CustomerEntity> customerEntity = customerRepositories.findById(addCartCommand.getCustomerId());

        if (customerEntity.isPresent()) {
            CartEntity cartEntity = cartRepositories.findByCustomerId(addCartCommand.getCustomerId());

            if (cartEntity != null)
                throw new CartException(StatusCode.C7001);

            CartEntity newCartEntity = new CartEntity(
                    addCartCommand.getCartId(),
                    addCartCommand.getCustomerId());
            cartRepositories.save(newCartEntity);

            return new AddCartCommand(addCartCommand.getCartId(), addCartCommand.getCustomerId());
        }else {
            throw new CartException(StatusCode.C5000);
        }
    }
}
