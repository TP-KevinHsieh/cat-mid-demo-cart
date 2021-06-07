package com.cat.mid.demo.cart.cartms.interfaces.rest;

import com.cat.mid.demo.cart.cartms.application.internal.commandservices.AddOrderCommandService;
import com.cat.mid.demo.cart.cartms.domain.commands.AddOrderCommand;
import com.cat.mid.demo.cart.cartms.domain.commands.AddOrderToServerCommand;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.orderadd.OrderAddReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.orderadd.OrderAddRespDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.transform.CartAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat-mid-demo-cart")
public class OrderCommandController {
    @Autowired
    private AddOrderCommandService addOrderCommandService;

    @PostMapping("/order/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderAddRespDto> addOrder(@RequestBody OrderAddReqDto orderAddReqDto) {
        AddOrderCommand addOrderCommand = CartAssembler.toAddOrderCommand(orderAddReqDto);
        AddOrderToServerCommand addOrderToServerCommand = addOrderCommandService.addOrder(addOrderCommand);
        OrderAddRespDto orderAddRespDto = CartAssembler.toOrderAddRespDto(addOrderToServerCommand);

        return new ResponseEntity<>(orderAddRespDto, HttpStatus.OK);
    }
}
