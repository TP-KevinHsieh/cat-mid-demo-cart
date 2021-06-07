package com.cat.mid.demo.cart.cartms.interfaces.rest;

import com.cat.mid.demo.cart.cartms.application.internal.commandservices.AddCartGoodsCommandService;
import com.cat.mid.demo.cart.cartms.application.internal.commandservices.DeleteCartGoodsCommandService;
import com.cat.mid.demo.cart.cartms.application.internal.commandservices.UpdateCartGoodsCommandService;
import com.cat.mid.demo.cart.cartms.domain.commands.AddCartGoodsCommand;
import com.cat.mid.demo.cart.cartms.domain.commands.DeleteCartGoodsCommand;
import com.cat.mid.demo.cart.cartms.domain.commands.UpdateCartGoodsCommand;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsadd.CartGoodsAddReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsadd.CartGoodsAddRespDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsdelete.CartGoodsDeleteReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsdelete.CartGoodsDeleteRespDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsupdate.CartGoodsUpdateReqDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.dto.cartgoodsupdate.CartGoodsUpdateRespDto;
import com.cat.mid.demo.cart.cartms.interfaces.rest.transform.CartAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat-mid-demo-cart")
public class CartGoodsCommandController {
    @Autowired
    private AddCartGoodsCommandService addCartGoodsCommandService;

    @Autowired
    private UpdateCartGoodsCommandService updateCartGoodsCommandService;

    @Autowired
    private DeleteCartGoodsCommandService deleteCartGoodsCommandService;

    @PostMapping("/cartgoods/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CartGoodsAddRespDto> addCartGoods(@RequestBody CartGoodsAddReqDto cartGoodsAddReqDto){
        AddCartGoodsCommand addCartGoodsCommand = CartAssembler.toAddCartGoodsCommand(cartGoodsAddReqDto);
        addCartGoodsCommandService.addCartGoods(addCartGoodsCommand);

        return new ResponseEntity<>(new CartGoodsAddRespDto(), HttpStatus.OK);
    }

    @PostMapping("/cartgoods/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CartGoodsUpdateRespDto> updateCartGoods(@RequestBody CartGoodsUpdateReqDto cartGoodsUpdateReqDto){
        UpdateCartGoodsCommand updateCartGoodsCommand = CartAssembler.toUpdateCartGoodsCommand(cartGoodsUpdateReqDto);
        updateCartGoodsCommandService.updateCartGoods(updateCartGoodsCommand);

        return new ResponseEntity<>(new CartGoodsUpdateRespDto(), HttpStatus.OK);
    }

    @PostMapping("/cartgoods/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CartGoodsDeleteRespDto> deleteCartGoods(@RequestBody CartGoodsDeleteReqDto cartGoodsDeleteReqDto){
        DeleteCartGoodsCommand deleteCartGoodsCommand = CartAssembler.toDeleteCartGoodsCommand(cartGoodsDeleteReqDto);
        deleteCartGoodsCommandService.deleteCartGoods(deleteCartGoodsCommand);

        return new ResponseEntity<>(new CartGoodsDeleteRespDto(), HttpStatus.OK);
    }
}
