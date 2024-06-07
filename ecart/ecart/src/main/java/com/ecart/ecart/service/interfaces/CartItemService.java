package com.ecart.ecart.service.interfaces;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;

import java.util.List;

public interface CartItemService {

    //create methods

    CartItems addToCart(Integer itemId, Integer userId, Integer count) throws EntityNotFoundException, EntityAlreadyExistsException, InvalidInputException;

    //read methods
    Integer getCartItemId(Integer itemId, Integer userId) throws EntityNotFoundException;

    CartItems getCartItemById(Integer cartItemId) throws EntityNotFoundException;

    List<CartItems> getUserCart(Integer userId) throws EntityNotFoundException;

    Boolean checkItemInUserCart(Integer itemId, Integer userId) throws EntityNotFoundException;

    //update
    CartItems removeFromCart(Integer cartItemId, Integer count) throws EntityNotFoundException, InvalidInputException;


    //delete method
    CartItems deleteCartItem(Integer cartItemId) throws EntityNotFoundException;
}
