package com.ecart.ecart.dto;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Ratings;
import com.ecart.ecart.entity.Reviews;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserModel.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserModelDiffblueTest {
    @Autowired
    private UserModel userModel;

    /**
     * Method under test: {@link UserModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList() {
        // Arrange and Act
        List<UserModel> actualParseModelListFromEntityListResult = UserModel
                .parseModelListFromEntityList(new ArrayList<>());

        // Assert
        assertTrue(actualParseModelListFromEntityListResult.isEmpty());
    }

    /**
     * Method under test: {@link UserModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList2() {
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

        ArrayList<Users> userList = new ArrayList<>();
        userList.add(users);

        // Act
        List<UserModel> actualParseModelListFromEntityListResult = UserModel.parseModelListFromEntityList(userList);

        // Assert
        assertEquals(1, actualParseModelListFromEntityListResult.size());
    }

    /**
     * Method under test: {@link UserModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList3() {
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

        Users users2 = new Users();
        users2.setAddress("17 High St");
        users2.setAge(0L);
        users2.setCartEntriesList(new ArrayList<>());
        users2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users2.setEmail("john.smith@example.org");
        users2.setGender(Gender.FEMALE);
        users2.setPassword("Password");
        users2.setRatingsList(new ArrayList<>());
        users2.setReviewsList(new ArrayList<>());
        users2.setUserId(2);
        users2.setUserName("User Name");

        ArrayList<Users> userList = new ArrayList<>();
        userList.add(users2);
        userList.add(users);

        // Act
        List<UserModel> actualParseModelListFromEntityListResult = UserModel.parseModelListFromEntityList(userList);

        // Assert
        assertEquals(2, actualParseModelListFromEntityListResult.size());
    }

    /**
     * Method under test: {@link UserModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList4() {
        // Arrange
        Users users = mock(Users.class);
        when(users.getGender()).thenReturn(Gender.MALE);
        when(users.getUserId()).thenReturn(1);
        when(users.getAge()).thenReturn(1L);
        when(users.getAddress()).thenReturn("42 Main St");
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        when(users.getPassword()).thenReturn("iloveyou");
        when(users.getUserName()).thenReturn("janedoe");
        when(users.getDateOfBirth()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(users).setAddress(Mockito.<String>any());
        doNothing().when(users).setAge(Mockito.<Long>any());
        doNothing().when(users).setCartEntriesList(Mockito.<List<CartItems>>any());
        doNothing().when(users).setDateOfBirth(Mockito.<LocalDate>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setGender(Mockito.<Gender>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRatingsList(Mockito.<List<Ratings>>any());
        doNothing().when(users).setReviewsList(Mockito.<List<Reviews>>any());
        doNothing().when(users).setUserId(Mockito.<Integer>any());
        doNothing().when(users).setUserName(Mockito.<String>any());
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

        ArrayList<Users> userList = new ArrayList<>();
        userList.add(users);

        // Act
        List<UserModel> actualParseModelListFromEntityListResult = UserModel.parseModelListFromEntityList(userList);

        // Assert
        verify(users).getAddress();
        verify(users).getAge();
        verify(users).getDateOfBirth();
        verify(users).getEmail();
        verify(users).getGender();
        verify(users).getPassword();
        verify(users).getUserId();
        verify(users).getUserName();
        verify(users).setAddress(eq("42 Main St"));
        verify(users).setAge(eq(1L));
        verify(users).setCartEntriesList(isA(List.class));
        verify(users).setDateOfBirth(isA(LocalDate.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setGender(eq(Gender.MALE));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRatingsList(isA(List.class));
        verify(users).setReviewsList(isA(List.class));
        verify(users).setUserId(eq(1));
        verify(users).setUserName(eq("janedoe"));
        assertEquals(1, actualParseModelListFromEntityListResult.size());
    }

    /**
     * Method under test: {@link UserModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList5() {
        // Arrange
        Users users = mock(Users.class);
        when(users.getGender()).thenThrow(new IllegalArgumentException("foo"));
        when(users.getUserId()).thenReturn(1);
        when(users.getAge()).thenReturn(1L);
        when(users.getAddress()).thenReturn("42 Main St");
        when(users.getEmail()).thenReturn("jane.doe@example.org");
        when(users.getPassword()).thenReturn("iloveyou");
        when(users.getUserName()).thenReturn("janedoe");
        when(users.getDateOfBirth()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(users).setAddress(Mockito.<String>any());
        doNothing().when(users).setAge(Mockito.<Long>any());
        doNothing().when(users).setCartEntriesList(Mockito.<List<CartItems>>any());
        doNothing().when(users).setDateOfBirth(Mockito.<LocalDate>any());
        doNothing().when(users).setEmail(Mockito.<String>any());
        doNothing().when(users).setGender(Mockito.<Gender>any());
        doNothing().when(users).setPassword(Mockito.<String>any());
        doNothing().when(users).setRatingsList(Mockito.<List<Ratings>>any());
        doNothing().when(users).setReviewsList(Mockito.<List<Reviews>>any());
        doNothing().when(users).setUserId(Mockito.<Integer>any());
        doNothing().when(users).setUserName(Mockito.<String>any());
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

        ArrayList<Users> userList = new ArrayList<>();
        userList.add(users);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> UserModel.parseModelListFromEntityList(userList));
        verify(users).getAddress();
        verify(users).getAge();
        verify(users).getDateOfBirth();
        verify(users).getEmail();
        verify(users).getGender();
        verify(users).getPassword();
        verify(users).getUserId();
        verify(users).getUserName();
        verify(users).setAddress(eq("42 Main St"));
        verify(users).setAge(eq(1L));
        verify(users).setCartEntriesList(isA(List.class));
        verify(users).setDateOfBirth(isA(LocalDate.class));
        verify(users).setEmail(eq("jane.doe@example.org"));
        verify(users).setGender(eq(Gender.MALE));
        verify(users).setPassword(eq("iloveyou"));
        verify(users).setRatingsList(isA(List.class));
        verify(users).setReviewsList(isA(List.class));
        verify(users).setUserId(eq(1));
        verify(users).setUserName(eq("janedoe"));
    }

    /**
     * Method under test: {@link UserModel#parseEntityFromModel()}
     */
    @Test
    void testParseEntityFromModel() throws InvalidInputException {
        // Arrange, Act and Assert
        assertThrows(InvalidInputException.class, () -> userModel.parseEntityFromModel());
    }

    /**
     * Method under test: {@link UserModel#isModelValid()}
     */
    @Test
    void testIsModelValid() throws InvalidInputException {
        // Arrange, Act and Assert
        assertThrows(InvalidInputException.class, () -> userModel.isModelValid());
    }

    /**
     * Method under test: {@link UserModel#validateEmail(String)}
     */
    @Test
    void testValidateEmail() throws InvalidInputException {
        // Arrange, Act and Assert
        assertTrue(userModel.validateEmail("jane.doe@example.org"));
        assertTrue(userModel.validateEmail("john.smith@example.org"));
        assertTrue(userModel.validateEmail("prof.einstein@example.org"));
        assertTrue(userModel.validateEmail("U@U.UUUU"));
        assertThrows(InvalidInputException.class,
                () -> userModel.validateEmail("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"));
        assertThrows(InvalidInputException.class, () -> userModel.validateEmail(null));
        assertThrows(InvalidInputException.class, () -> userModel.validateEmail(""));
        assertTrue(userModel.validateEmail("jane.doe@example.orgcom.ecart.ecart.entity.Users"));
        assertTrue(userModel.validateEmail("john.smith@example.orgcom.ecart.ecart.entity.Users"));
        assertTrue(userModel.validateEmail("prof.einstein@example.orgcom.ecart.ecart.entity.Users"));
        assertTrue(userModel.validateEmail("U@U.UUUUcom.ecart.ecart.entity.Users"));
        assertTrue(userModel.validateEmail("Emailjane.doe@example.org"));
        assertTrue(userModel.validateEmail("Emailjohn.smith@example.org"));
        assertTrue(userModel.validateEmail("Emailprof.einstein@example.org"));
        assertTrue(userModel.validateEmail("EmailU@U.UUUU"));
        assertTrue(userModel.validateEmail("42jane.doe@example.org"));
        assertTrue(userModel.validateEmail("42john.smith@example.org"));
        assertTrue(userModel.validateEmail("42prof.einstein@example.org"));
        assertTrue(userModel.validateEmail("42U@U.UUUU"));
        assertTrue(userModel.validateEmail("com.ecart.ecart.entity.Usersjane.doe@example.org"));
        assertTrue(userModel.validateEmail("com.ecart.ecart.entity.Usersjohn.smith@example.org"));
        assertTrue(userModel.validateEmail("com.ecart.ecart.entity.Usersprof.einstein@example.org"));
        assertTrue(userModel.validateEmail("com.ecart.ecart.entity.UsersU@U.UUUU"));
        assertTrue(userModel.validateEmail("com.ecart.ecart.model.UserModeljane.doe@example.org"));
        assertTrue(userModel.validateEmail("com.ecart.ecart.model.UserModeljohn.smith@example.org"));
        assertTrue(userModel.validateEmail("com.ecart.ecart.model.UserModelprof.einstein@example.org"));
        assertTrue(userModel.validateEmail("com.ecart.ecart.model.UserModelU@U.UUUU"));
        assertTrue(userModel.validateEmail("dd-MM-yyyyjane.doe@example.org"));
        assertTrue(userModel.validateEmail("dd-MM-yyyyjohn.smith@example.org"));
        assertTrue(userModel.validateEmail("dd-MM-yyyyprof.einstein@example.org"));
        assertTrue(userModel.validateEmail("dd-MM-yyyyU@U.UUUU"));
    }

    /**
     * Method under test: {@link UserModel#validateUserName(String)}
     */
    @Test
    void testValidateUserName() throws InvalidInputException {
        // Arrange, Act and Assert
        assertTrue(userModel.validateUserName("janedoe"));
        assertThrows(InvalidInputException.class, () -> userModel.validateUserName(null));
        assertThrows(InvalidInputException.class, () -> userModel.validateUserName(""));
        assertThrows(InvalidInputException.class,
                () -> userModel.validateUserName("janedoe^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"));
    }

    /**
     * Method under test: {@link UserModel#validatePassword(String)}
     */
    @Test
    void testValidatePassword() throws InvalidInputException {
        // Arrange, Act and Assert
        assertTrue(userModel.validatePassword("iloveyou"));
        assertThrows(InvalidInputException.class, () -> userModel.validatePassword(null));
        assertThrows(InvalidInputException.class, () -> userModel.validatePassword(""));
    }

    /**
     * Method under test: {@link UserModel#validateAddress(String)}
     */
    @Test
    void testValidateAddress() throws InvalidInputException {
        // Arrange, Act and Assert
        assertThrows(InvalidInputException.class, () -> userModel.validateAddress("42 Main St"));
    }

    /**
     * Method under test: {@link UserModel#validateGender(String)}
     */
    @Test
    void testValidateGender() throws InvalidInputException {
        // Arrange, Act and Assert
        assertThrows(InvalidInputException.class, () -> userModel.validateGender("Gender"));
    }

    /**
     * Method under test: {@link UserModel#validateDateOfBirth(String)}
     */
    @Test
    void testValidateDateOfBirth() throws InvalidInputException {
        // Arrange, Act and Assert
        assertTrue(userModel.validateDateOfBirth("2020-03-01"));
        assertThrows(InvalidInputException.class, () -> userModel.validateDateOfBirth("2020/03/01"));
    }

    /**
     * Method under test: {@link UserModel#UserModel(Users)}
     */
    @Test
    void testNewUserModel() {
        // Arrange
        Users user = new Users();
        user.setAddress("42 Main St");
        user.setAge(1L);
        user.setCartEntriesList(new ArrayList<>());
        user.setDateOfBirth(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.MALE);
        user.setPassword("iloveyou");
        user.setRatingsList(new ArrayList<>());
        user.setReviewsList(new ArrayList<>());
        user.setUserId(1);
        user.setUserName("janedoe");

        // Act
        UserModel actualUserModel = new UserModel(user);

        // Assert
        assertEquals("1970-01-01", actualUserModel.getDateOfBirth());
        assertEquals("42 Main St", actualUserModel.getAddress());
        assertEquals("MALE", actualUserModel.getGender());
        assertEquals("iloveyou", actualUserModel.getPassword());
        assertEquals("jane.doe@example.org", actualUserModel.getEmail());
        assertEquals("janedoe", actualUserModel.getUserName());
        assertEquals(1, actualUserModel.getUserId().intValue());
    }

    /**
     * Method under test: {@link UserModel#UserModel(Users)}
     */
    @Test
    void testNewUserModel2() {
        // Arrange
        Users user = mock(Users.class);
        when(user.getGender()).thenReturn(Gender.MALE);
        when(user.getUserId()).thenReturn(1);
        when(user.getAge()).thenReturn(1L);
        when(user.getAddress()).thenReturn("42 Main St");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUserName()).thenReturn("janedoe");
        when(user.getDateOfBirth()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(user).setAddress(Mockito.<String>any());
        doNothing().when(user).setAge(Mockito.<Long>any());
        doNothing().when(user).setCartEntriesList(Mockito.<List<CartItems>>any());
        doNothing().when(user).setDateOfBirth(Mockito.<LocalDate>any());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setGender(Mockito.<Gender>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRatingsList(Mockito.<List<Ratings>>any());
        doNothing().when(user).setReviewsList(Mockito.<List<Reviews>>any());
        doNothing().when(user).setUserId(Mockito.<Integer>any());
        doNothing().when(user).setUserName(Mockito.<String>any());
        user.setAddress("42 Main St");
        user.setAge(1L);
        user.setCartEntriesList(new ArrayList<>());
        user.setDateOfBirth(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.MALE);
        user.setPassword("iloveyou");
        user.setRatingsList(new ArrayList<>());
        user.setReviewsList(new ArrayList<>());
        user.setUserId(1);
        user.setUserName("janedoe");

        // Act
        UserModel actualUserModel = new UserModel(user);

        // Assert
        verify(user).getAddress();
        verify(user).getAge();
        verify(user).getDateOfBirth();
        verify(user).getEmail();
        verify(user).getGender();
        verify(user).getPassword();
        verify(user).getUserId();
        verify(user).getUserName();
        verify(user).setAddress(eq("42 Main St"));
        verify(user).setAge(eq(1L));
        verify(user).setCartEntriesList(isA(List.class));
        verify(user).setDateOfBirth(isA(LocalDate.class));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setGender(eq(Gender.MALE));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRatingsList(isA(List.class));
        verify(user).setReviewsList(isA(List.class));
        verify(user).setUserId(eq(1));
        verify(user).setUserName(eq("janedoe"));
        assertEquals("1970-01-01", actualUserModel.getDateOfBirth());
        assertEquals("42 Main St", actualUserModel.getAddress());
        assertEquals("MALE", actualUserModel.getGender());
        assertEquals("iloveyou", actualUserModel.getPassword());
        assertEquals("jane.doe@example.org", actualUserModel.getEmail());
        assertEquals("janedoe", actualUserModel.getUserName());
        assertEquals(1, actualUserModel.getUserId().intValue());
        assertEquals(1L, actualUserModel.getAge().longValue());
    }

    /**
     * Method under test: {@link UserModel#UserModel(Users)}
     */
    @Test
    void testNewUserModel3() {
        // Arrange
        Users user = mock(Users.class);
        when(user.getGender()).thenThrow(new IllegalArgumentException("foo"));
        when(user.getUserId()).thenReturn(1);
        when(user.getAge()).thenReturn(1L);
        when(user.getAddress()).thenReturn("42 Main St");
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUserName()).thenReturn("janedoe");
        when(user.getDateOfBirth()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(user).setAddress(Mockito.<String>any());
        doNothing().when(user).setAge(Mockito.<Long>any());
        doNothing().when(user).setCartEntriesList(Mockito.<List<CartItems>>any());
        doNothing().when(user).setDateOfBirth(Mockito.<LocalDate>any());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setGender(Mockito.<Gender>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRatingsList(Mockito.<List<Ratings>>any());
        doNothing().when(user).setReviewsList(Mockito.<List<Reviews>>any());
        doNothing().when(user).setUserId(Mockito.<Integer>any());
        doNothing().when(user).setUserName(Mockito.<String>any());
        user.setAddress("42 Main St");
        user.setAge(1L);
        user.setCartEntriesList(new ArrayList<>());
        user.setDateOfBirth(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.MALE);
        user.setPassword("iloveyou");
        user.setRatingsList(new ArrayList<>());
        user.setReviewsList(new ArrayList<>());
        user.setUserId(1);
        user.setUserName("janedoe");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new UserModel(user));
        verify(user).getAddress();
        verify(user).getAge();
        verify(user).getDateOfBirth();
        verify(user).getEmail();
        verify(user).getGender();
        verify(user).getPassword();
        verify(user).getUserId();
        verify(user).getUserName();
        verify(user).setAddress(eq("42 Main St"));
        verify(user).setAge(eq(1L));
        verify(user).setCartEntriesList(isA(List.class));
        verify(user).setDateOfBirth(isA(LocalDate.class));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setGender(eq(Gender.MALE));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRatingsList(isA(List.class));
        verify(user).setReviewsList(isA(List.class));
        verify(user).setUserId(eq(1));
        verify(user).setUserName(eq("janedoe"));
    }
}
