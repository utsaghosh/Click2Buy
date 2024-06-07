package com.ecart.ecart.service.impl;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.Auth;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.dto.UserModel;
import com.ecart.ecart.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServicePrimaryImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserServicePrimaryImplDiffblueTest {
    @Autowired
    private UserServicePrimaryImpl userServicePrimaryImpl;

    @MockBean
    private UsersRepository usersRepository;

    /**
     * Method under test: {@link UserServicePrimaryImpl#addUser(UserModel)}
     */
    @Test
    void testAddUser() throws EntityAlreadyExistsException, InvalidInputException {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("jane.doe@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(usersRepository.findByEmail(Mockito.<String>any())).thenReturn(users);
        UserModel userModel = mock(UserModel.class);
        when(userModel.getEmail()).thenReturn("jane.doe@example.org");
        when(userModel.isModelValid()).thenReturn(true);

        // Act and Assert
        assertThrows(EntityAlreadyExistsException.class, () -> userServicePrimaryImpl.addUser(userModel));
        verify(userModel).getEmail();
        verify(userModel).isModelValid();
        verify(usersRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserServicePrimaryImpl#addUser(UserModel)}
     */
    @Test
    void testAddUser2() throws EntityAlreadyExistsException, InvalidInputException {
        // Arrange
        UserModel userModel = mock(UserModel.class);
        when(userModel.isModelValid()).thenReturn(false);

        // Act
        Users actualAddUserResult = userServicePrimaryImpl.addUser(userModel);

        // Assert
        verify(userModel).isModelValid();
        assertNull(actualAddUserResult);
    }

    /**
     * Method under test: {@link UserServicePrimaryImpl#getUserById(Integer)}
     */
    @Test
    void testGetUserById() throws EntityNotFoundException {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("jane.doe@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        Optional<Users> ofResult = Optional.of(users);
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Users actualUserById = userServicePrimaryImpl.getUserById(1);

        // Assert
        verify(usersRepository).findById(eq(1));
        assertSame(users, actualUserById);
    }

    /**
     * Method under test: {@link UserServicePrimaryImpl#getUserById(Integer)}
     */
    @Test
    void testGetUserById2() throws EntityNotFoundException {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> userServicePrimaryImpl.getUserById(1));
        verify(usersRepository).findById(eq(1));
    }

    /**
     * Method under test: {@link UserServicePrimaryImpl#getUserCart(Integer)}
     */
    @Test
    void testGetUserCart() throws EntityNotFoundException {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        ArrayList<CartItems> cartEntriesList = new ArrayList<>();
        users.setCartEntriesList(cartEntriesList);
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("jane.doe@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        Optional<Users> ofResult = Optional.of(users);
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        List<CartItems> actualUserCart = userServicePrimaryImpl.getUserCart(1);

        // Assert
        verify(usersRepository).findById(eq(1));
        assertTrue(actualUserCart.isEmpty());
        assertSame(cartEntriesList, actualUserCart);
    }

    /**
     * Method under test: {@link UserServicePrimaryImpl#getUserCart(Integer)}
     */
    @Test
    void testGetUserCart2() throws EntityNotFoundException {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> userServicePrimaryImpl.getUserCart(1));
        verify(usersRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link UserServicePrimaryImpl#updateUserDetails(Integer, UserModel)}
     */
    @Test
    void testUpdateUserDetails() throws EntityNotFoundException, InvalidInputException {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> userServicePrimaryImpl.updateUserDetails(1, new UserModel()));
        verify(usersRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link UserServicePrimaryImpl#updateUserDetails(Integer, UserModel)}
     */
    @Test
    void testUpdateUserDetails2() throws EntityNotFoundException, InvalidInputException {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("jane.doe@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        Optional<Users> ofResult = Optional.of(users);
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        UserModel userModel = mock(UserModel.class);
        when(userModel.isModelValid()).thenReturn(false);

        // Act
        Users actualUpdateUserDetailsResult = userServicePrimaryImpl.updateUserDetails(1, userModel);

        // Assert
        verify(userModel).isModelValid();
        verify(usersRepository).findById(eq(1));
        assertNull(actualUpdateUserDetailsResult);
    }

    /**
     * Method under test: {@link UserServicePrimaryImpl#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser() throws EntityNotFoundException {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("jane.doe@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        Optional<Users> ofResult = Optional.of(users);
        doNothing().when(usersRepository).deleteById(Mockito.<Integer>any());
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Users actualDeleteUserResult = userServicePrimaryImpl.deleteUser(1);

        // Assert
        verify(usersRepository).deleteById(eq(1));
        verify(usersRepository).findById(eq(1));
        assertSame(users, actualDeleteUserResult);
    }

    /**
     * Method under test: {@link UserServicePrimaryImpl#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser2() throws EntityNotFoundException {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> userServicePrimaryImpl.deleteUser(1));
        verify(usersRepository).findById(eq(1));
    }

    /**
     * Method under test: {@link UserServicePrimaryImpl#userAuthentication(Auth)}
     */
    @Test
    void testUserAuthentication() throws EntityNotFoundException {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("jane.doe@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(usersRepository.findByEmailAndPassword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(users);

        // Act
        Users actualUserAuthenticationResult = userServicePrimaryImpl
                .userAuthentication(new Auth("jane.doe@example.org", "iloveyou"));

        // Assert
        verify(usersRepository).findByEmailAndPassword(eq("jane.doe@example.org"), eq("iloveyou"));
        assertSame(users, actualUserAuthenticationResult);
    }
}
