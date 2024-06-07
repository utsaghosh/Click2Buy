package com.ecart.ecart.controller;

import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.AddToCart;
import com.ecart.ecart.dto.CartItemModel;
import com.ecart.ecart.service.interfaces.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/carts")
@Slf4j
public class CartController {

    @Autowired
    CartItemService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItemModel> addToCart(@RequestBody AddToCart data) throws InvalidInputException, EntityAlreadyExistsException, EntityNotFoundException {
        log.info("Request add to cart: {} item({}) to cart of user({})", data.getCount(), data.getItemId(), data.getUserId());
        CartItemModel cartItem = new CartItemModel(cartService.addToCart(data.getItemId(), data.getUserId(), data.getCount()));
        log.info("Response new cartItem created : {}", cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @GetMapping("getid/{itemid}/{userid}")
    public ResponseEntity<Integer> getCartItemId(@PathVariable("itemid") Integer itemId, @PathVariable("userid") Integer userId) throws EntityNotFoundException {
        log.info("Request get id of cartItem whose itemId={}, userId={}", itemId, userId);
        Integer id = cartService.getCartItemId(itemId, userId);
        log.info("Response found id={}", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("getbyid/{cartitemid}")
    public ResponseEntity<CartItemModel> getCartItemById(@PathVariable("cartitemid") Integer cartItemId) throws EntityNotFoundException {
        log.info("Request cartItem with id={}", cartItemId);
        CartItemModel cartItem = new CartItemModel(cartService.getCartItemById(cartItemId));
        log.info("Response found : {}", cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @GetMapping("getusercart/{userid}")
    public ResponseEntity<List<CartItemModel>> getUserCart(@PathVariable("userid") Integer userId) throws EntityNotFoundException {
        log.info("Request cart of user={}", userId);
        List<CartItemModel> cart = CartItemModel.parseModelListFromEntityList(cartService.getUserCart(userId));
        log.info("Response cart: {}", cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/check/{itemid}/{userid}")
    public ResponseEntity<Boolean> checkItemInUserCart(@PathVariable("itemid") Integer itemId, @PathVariable("userid") Integer userId) throws EntityNotFoundException {
        log.info("Request check if item={} in cart of user={}", itemId, userId);
        Boolean check = cartService.checkItemInUserCart(itemId, userId);
        log.info("Response: {}", check);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

    @PostMapping("/remove/{cartitemid}/{count}")
    public ResponseEntity<CartItemModel> removeFromCart(@PathVariable("cartitemid") Integer cartItemId, @PathVariable("count") Integer count) throws InvalidInputException, EntityNotFoundException {
        log.info("Request remove {} items from cartItem={}", count, cartItemId);
        CartItemModel cartItem = new CartItemModel(cartService.removeFromCart(cartItemId, count));
        log.info("Response updated cartItem : {}", cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartitemid}")
    public ResponseEntity<CartItemModel> delete(@PathVariable("cartitemid") Integer cartItemId) throws EntityNotFoundException {
        log.info("Request delete cartItem={}",cartItemId);
        CartItemModel deleted = new CartItemModel(cartService.deleteCartItem(cartItemId));
        log.info("Response deleted: {}", deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
