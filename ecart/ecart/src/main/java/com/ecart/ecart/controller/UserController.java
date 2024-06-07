package com.ecart.ecart.controller;

import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.Auth;
import com.ecart.ecart.dto.CartItemModel;
import com.ecart.ecart.dto.UserModel;
import com.ecart.ecart.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel userInput) throws InvalidInputException, EntityAlreadyExistsException {

        log.info("Requesting adding new user with details : {}", userInput);

        UserModel added = new UserModel(userService.addUser(userInput));

        log.info("New User added with details: {}", added);

        return new ResponseEntity<UserModel>(added, HttpStatus.OK);
    }

    @GetMapping("/get/{userid}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("userid") Integer userId) throws EntityNotFoundException {

        log.info("Requested user with id={}", userId);

        UserModel user = new UserModel(userService.getUserById(userId));

        log.info("User found: {}", user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getcart/{userid}")
    public ResponseEntity<List<CartItemModel>> getUserCart(@PathVariable("userid") Integer userId) throws EntityNotFoundException {

        log.info("Requesting cart of user with id={}", userId);

        List<CartItemModel> cart = CartItemModel.parseModelListFromEntityList(userService.getUserCart(userId));

        log.info("Cart of user({}) : {}", userId, cart);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/update/{userid}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("userid") Integer userId, @RequestBody UserModel userInput) throws InvalidInputException, EntityNotFoundException {

        log.info("Requesting updation of user(id = {}) with {}", userId, userInput);

        UserModel updated = new UserModel(userService.updateUserDetails(userId,userInput));

        log.info("Updated user: {}", updated);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable("userid") Integer userId) throws EntityNotFoundException {

        log.info("Requesting deletion of user(id = {})", userId);

        UserModel deleted = new UserModel(userService.deleteUser(userId));

        log.info("Deleted user: {}", deleted);

        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<UserModel> authenticateUser(@RequestBody Auth auth) throws EntityNotFoundException {

        log.info("Request to authenticate user with {}", auth);

        UserModel user = new UserModel(userService.userAuthentication(auth));

        log.info("Authenticated user: {}", user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
