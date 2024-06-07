package com.ecart.ecart.service.impl;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.Auth;
import com.ecart.ecart.dto.UserModel;
import com.ecart.ecart.repository.UsersRepository;
import com.ecart.ecart.service.interfaces.UserService;
import com.ecart.ecart.dto.Gender;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@Qualifier("userService")
@Slf4j
public class UserServicePrimaryImpl implements UserService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users addUser(UserModel userModel) throws EntityAlreadyExistsException, InvalidInputException {

        log.info("Adding new user with: {}", userModel);

        if(! userModel.isModelValid()) {
            return null;
        }

        Users existingUser = usersRepository.findByEmail(userModel.getEmail());

        if(existingUser != null) {
            log.info("New user not added");
            throw new EntityAlreadyExistsException("Another user exists with same email, may edit user details");
        }

        Users user = userModel.parseEntityFromModel();
        user = usersRepository.save(user);

        log.info("New user added successfully with id = {}", user.getUserId());
        return user;
    }

    @Override
    public Users getUserById(Integer userId) throws EntityNotFoundException {

        log.info("Fetching user with id = {}", userId);
        Users user = usersRepository.findById(userId).orElse(null);
        if(user==null) {
            throw new EntityNotFoundException("User not found");
        }
        log.info("User with id = {}, name = {} fetched successfully", user.getUserId(), user.getUserName());
        return user;
    }

    @Override
    public List<CartItems> getUserCart(Integer userId) throws EntityNotFoundException {

        log.info("Fetching cart of user with id = {}", userId);
        List<CartItems> userCart = this.getUserById(userId).getCartEntriesList();
//        if(userCart.isEmpty()) {
//            throw new EntityNotFoundException("User's cart is empty");
//        }
        log.info("Fetched cart of user with id = {}, {} items found", userId, userCart.size());
        return userCart;
    }

    @Override
    public Users updateUserDetails(Integer userId, UserModel userModel) throws EntityNotFoundException, InvalidInputException {
        
        log.info("Updating user, id={}, with update= {}", userId, userModel);

        Users user = this.getUserById(userId);

        if(user == null) {
            log.info("User not updated");
            throw new EntityNotFoundException("No such user found");
        }

        if(! userModel.isModelValid()) {
            return null;
        }

        user.setDateOfBirth(LocalDate.parse(userModel.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        user.setEmail(userModel.getEmail());
        user.setGender(Gender.valueOf(userModel.getGender().toUpperCase()));
        user.setPassword(userModel.getPassword());
        user.setAddress(userModel.getAddress());
        user.setUserName(userModel.getUserName());

        log.info("User updated");
        return usersRepository.save(user);
    }

    @Override
    public Users deleteUser(Integer userId) throws EntityNotFoundException {

        log.info("Deleting user requested, userId={}", userId);
        Users user = this.getUserById(userId);

        if(user == null) {
            log.info("User not deleted");
            throw new EntityNotFoundException("No such user found");
        }

        usersRepository.deleteById(user.getUserId());

        log.info("User with id = {}, name = {} deleted successfully", user.getUserId(), user.getUserName());
        return user;
    }

    @Override
    public Users userAuthentication(Auth auth) throws EntityNotFoundException {

        /*
         * Returns user instance if valid else null
         */
        log.info("Authentication requested for {}", auth);
        Users user = usersRepository.findByEmailAndPassword(auth.getEmail(), auth.getPassword());
        if(user == null) {
            log.info("Auth Failed");
            throw new EntityNotFoundException("No user exists with such credentials");
        }
        log.info("Auth Successful for user with id={}",  user.getUserId());
        return user;
    }
}
