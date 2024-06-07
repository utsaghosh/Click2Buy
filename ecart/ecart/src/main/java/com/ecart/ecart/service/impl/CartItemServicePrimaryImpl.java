package com.ecart.ecart.service.impl;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.repository.CartItemsRepository;
import com.ecart.ecart.service.interfaces.CartItemService;
import com.ecart.ecart.service.interfaces.ItemService;
import com.ecart.ecart.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Qualifier("cartService")
@Slf4j
public class CartItemServicePrimaryImpl implements CartItemService {

    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    CartItemsRepository cartRepository;

    @Override
    public CartItems addToCart(Integer itemId, Integer userId, Integer count) throws EntityNotFoundException, EntityAlreadyExistsException, InvalidInputException {

        log.info("Adding item[id={}] to cart of user[id={}]", itemId, userId);

        Items item = itemService.getItemById(itemId);
        log.info("Item Found");

        Users user = userService.getUserById(userId);
        log.info("User Found");

        if(count <= 0){
            throw new InvalidInputException("Count must be >= 1");
        }

        Boolean existingEntry = this.checkItemInUserCart(item.getItemId(), user.getUserId());

        if(existingEntry) {
            log.info("Item already exists in user's cart, updating count");
            Integer cartItemId = this.getCartItemId(itemId,userId);

            CartItems cartItem = this.getCartItemById(cartItemId);
            cartItem.setItemCount(cartItem.getItemCount() + count);
            cartItem.setCreationDate(LocalDateTime.now());
            cartItem = cartRepository.save(cartItem);
            log.info("Count increased of cart item[id = {}] by {}", cartItem.getCartItemId(), count);
            return  cartItem;
        }
        else {
            CartItems cartItem = new CartItems();
            cartItem.setItem(item);
            cartItem.setUser(user);
            cartItem.setItemCount(count);
            cartItem.setCreationDate(LocalDateTime.now());
            cartItem.setItemPrice(item.getItemPrice());
            cartItem = cartRepository.save(cartItem);

            log.info("New cart item created with id = {}", cartItem.getCartItemId());
            return cartItem;
        }
    }

    @Override
    public Integer getCartItemId(Integer itemId, Integer userId) throws EntityNotFoundException {

        log.info("Fetching cart item id with itemid = {}, userid = {}", itemId, userId);

        Items item = itemService.getItemById(itemId);
        log.info("Item Found");
        Users user = userService.getUserById(userId);
        log.info("User Found");

        CartItems cartItem = cartRepository.findByItemIdAndUserId(item.getItemId(), user.getUserId());
        if(cartItem == null) {
            throw new EntityNotFoundException("No Such Cart Item Exists");
        }
        Integer cartItemId = cartItem.getCartItemId();
        log.info("Cart Item fetched successfully");
        return cartItemId;
    }

    @Override
    public CartItems getCartItemById(Integer cartItemId) throws EntityNotFoundException {

        log.info("Fetching cart item with id = {}", cartItemId);
        CartItems cartItem = cartRepository.findById(cartItemId).orElse(null);
        if(cartItem == null) {
            throw new EntityNotFoundException("No Such Cart Item Exists");
        }
        log.info("Cart Item fetched successfully");
        return cartItem;
    }

    @Override
    public List<CartItems> getUserCart(Integer userId) throws EntityNotFoundException {

        log.info("Fetching cart of user with id = {}", userId);

        Users user = userService.getUserById(userId);
        log.info("User Found");

        List<CartItems> userCart = cartRepository.findByUserId(user.getUserId());
        //        if(userCart.isEmpty()) {
//            throw new EntityNotFoundException("User's cart is empty");
//        }
        log.info("User Cart fetched successfully");
        return userCart;
    }

    @Override
    public Boolean checkItemInUserCart(Integer itemId, Integer userId) throws EntityNotFoundException {

        Items item = itemService.getItemById(itemId);
        log.info("Item Found");
        Users user = userService.getUserById(userId);
        log.info("User Found");

        try{
            Integer cartItemId = this.getCartItemId(item.getItemId(), user.getUserId());
            return true;
        }
        catch(EntityNotFoundException e){
            return false;
        }
    }

    @Override
    public CartItems removeFromCart(Integer cartItemId, Integer count) throws EntityNotFoundException, InvalidInputException {
        log.info("Removing item count {} from cart item with id={} ", count, cartItemId);

        CartItems cartItem = this.getCartItemById(cartItemId);

        if(count <= 0){
            throw new InvalidInputException("Count must be >= 1");
        }
        else if(count > cartItem.getItemCount()){
            throw new InvalidInputException("CartItem has lesser items than requested to be removed");
        }
        else if (count.equals(cartItem.getItemCount())) {
            return this.deleteCartItem(cartItemId);
        }
        else {
            cartItem.setItemCount(cartItem.getItemCount() - count);
            cartItem.setCreationDate(LocalDateTime.now());
            cartItem = cartRepository.save(cartItem);
            log.info("Count decreased of cart item[id = {}] by {}", cartItem.getCartItemId(), count);
            return  cartItem;
        }
    }

    @Override
    public CartItems deleteCartItem(Integer cartItemId) throws EntityNotFoundException {
        log.info("Deleting cart item with id={}", cartItemId);

        CartItems cartItem = this.getCartItemById(cartItemId);

        cartRepository.deleteById(cartItemId);

        log.info("Cart Item deleted successfully {}", cartItem);

        return cartItem;
    }
}
