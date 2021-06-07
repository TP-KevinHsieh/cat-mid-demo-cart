package com.cat.mid.demo.cart.cartms.interfaces.rest;

import com.cat.mid.demo.cart.cartms.application.internal.commandservices.AddCartCommandService;
import com.cat.mid.demo.cart.cartms.domain.commands.AddCartCommand;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartadd.CartAddReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartadd.CartAddRespDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.transform.CartAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat-mid-demo-cart")
public class CartCommandController {

    @Autowired
    private AddCartCommandService addCartCommandService;

    @PostMapping("/cart/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CartAddRespDto> addCart(@RequestBody CartAddReqDto cartAddReqDto){
        AddCartCommand addCartCommand = CartAssembler.toAddCartCommand(cartAddReqDto);
        CartAddRespDto cartAddRespDto = CartAssembler.toCartAddRespDto(addCartCommandService.addCart(addCartCommand));

        return new ResponseEntity<>(cartAddRespDto, HttpStatus.OK);
    }
}
