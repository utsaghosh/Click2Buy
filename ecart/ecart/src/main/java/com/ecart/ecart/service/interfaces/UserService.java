package com.ecart.ecart.service.interfaces;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.Auth;
import com.ecart.ecart.dto.UserModel;

import java.util.List;

public interface UserService {

    //create methods
    Users addUser(UserModel userModel) throws EntityAlreadyExistsException, InvalidInputException;


    //read methods
    Users getUserById(Integer userId) throws EntityNotFoundException;

    List<CartItems> getUserCart(Integer userId) throws EntityNotFoundException;


    //update methods
    Users updateUserDetails(Integer userId, UserModel userModel) throws EntityNotFoundException, InvalidInputException;


    //delete methods
    Users deleteUser(Integer userId) throws EntityNotFoundException;


    //Authentication: returns null or User entity
    Users userAuthentication(Auth auth) throws EntityNotFoundException;
}
