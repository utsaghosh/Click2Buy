package com.ecart.ecart.controller;

import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.dto.Auth;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.dto.UserModel;
import com.ecart.ecart.service.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserControllerDiffblueTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#getUserById(Integer)}
     */
    @Test
    void testGetUserById() throws Exception {
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
        when(userService.getUserById(Mockito.<Integer>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/get/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#getUserById(Integer)}
     */
    @Test
    void testGetUserById2() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("john.smith@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.getUserById(Mockito.<Integer>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/get/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"john.smith@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#getUserById(Integer)}
     */
    @Test
    void testGetUserById3() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("prof.einstein@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.getUserById(Mockito.<Integer>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/get/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"prof.einstein@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#getUserById(Integer)}
     */
    @Test
    void testGetUserById4() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("U@U.UUUU");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.getUserById(Mockito.<Integer>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/get/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"U@U.UUUU\",\"password\":\"iloveyou\",\"address\":\"42 Main"
                                        + " St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#getUserCart(Integer)}
     */
    @Test
    void testGetUserCart() throws Exception {
        // Arrange
        when(userService.getUserCart(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/getcart/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getUserCart(Integer)}
     */
    @Test
    void testGetUserCart2() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Requesting cart of user with id={}");
        item.setItemId(1);
        item.setItemImage("Requesting cart of user with id={}");
        item.setItemName("Requesting cart of user with id={}");
        item.setItemPrice(42);
        item.setRatingsList(new ArrayList<>());
        item.setReviewsList(new ArrayList<>());

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

        CartItems cartItems = new CartItems();
        cartItems.setCartItemId(1);
        cartItems.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        cartItems.setItem(item);
        cartItems.setItemCount(3);
        cartItems.setItemPrice(42);
        cartItems.setUser(user);

        ArrayList<CartItems> cartItemsList = new ArrayList<>();
        cartItemsList.add(cartItems);
        when(userService.getUserCart(Mockito.<Integer>any())).thenReturn(cartItemsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/getcart/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"cartItemId\":1,\"creationDate\":[1970,1,1,0,0],\"itemCount\":3,\"itemId\":1,\"itemName\":\"Requesting cart"
                                        + " of user with id={}\",\"itemImage\":\"Requesting cart of user with id={}\",\"itemPrice\":42,\"averageRating\""
                                        + ":0.0,\"userId\":1}]"));
    }

    /**
     * Method under test: {@link UserController#addUser(UserModel)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddUser() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.JsonMappingException: Gender can be MALE/FEMALE/OTHER only (through reference chain: com.ecart.ecart.model.UserModel["modelValid"])
        //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:402)
        //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:361)
        //       at com.fasterxml.jackson.databind.ser.std.StdSerializer.wrapAndThrow(StdSerializer.java:323)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:780)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   com.ecart.ecart.exception.InvalidInputException: Gender can be MALE/FEMALE/OTHER only
        //       at com.ecart.ecart.model.UserModel.validateGender(UserModel.java:163)
        //       at com.ecart.ecart.model.UserModel.isModelValid(UserModel.java:89)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:688)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        UserModel userModel = new UserModel();
        userModel.setAddress("42 Main St");
        userModel.setAge(1L);
        userModel.setDateOfBirth("2020-03-01");
        userModel.setEmail("jane.doe@example.org");
        userModel.setGender("Gender");
        userModel.setPassword("iloveyou");
        userModel.setUserId(1);
        userModel.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link UserController#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser() throws Exception {
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
        when(userService.deleteUser(Mockito.<Integer>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/delete/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser2() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("john.smith@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.deleteUser(Mockito.<Integer>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/delete/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"john.smith@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser3() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("prof.einstein@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.deleteUser(Mockito.<Integer>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/delete/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"prof.einstein@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser4() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("U@U.UUUU");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.deleteUser(Mockito.<Integer>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/delete/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"U@U.UUUU\",\"password\":\"iloveyou\",\"address\":\"42 Main"
                                        + " St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#authenticateUser(Auth)}
     */
    @Test
    void testAuthenticateUser() throws Exception {
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
        when(userService.userAuthentication(Mockito.<Auth>any())).thenReturn(users);

        Auth auth = new Auth();
        auth.setEmail("jane.doe@example.org");
        auth.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(auth);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#authenticateUser(Auth)}
     */
    @Test
    void testAuthenticateUser2() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("john.smith@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.userAuthentication(Mockito.<Auth>any())).thenReturn(users);

        Auth auth = new Auth();
        auth.setEmail("jane.doe@example.org");
        auth.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(auth);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"john.smith@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#authenticateUser(Auth)}
     */
    @Test
    void testAuthenticateUser3() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("prof.einstein@example.org");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.userAuthentication(Mockito.<Auth>any())).thenReturn(users);

        Auth auth = new Auth();
        auth.setEmail("jane.doe@example.org");
        auth.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(auth);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"prof.einstein@example.org\",\"password\":\"iloveyou\",\"address\":\"42"
                                        + " Main St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#authenticateUser(Auth)}
     */
    @Test
    void testAuthenticateUser4() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(1L);
        users.setCartEntriesList(new ArrayList<>());
        users.setDateOfBirth(LocalDate.of(1970, 1, 1));
        users.setEmail("U@U.UUUU");
        users.setGender(Gender.MALE);
        users.setPassword("iloveyou");
        users.setRatingsList(new ArrayList<>());
        users.setReviewsList(new ArrayList<>());
        users.setUserId(1);
        users.setUserName("janedoe");
        when(userService.userAuthentication(Mockito.<Auth>any())).thenReturn(users);

        Auth auth = new Auth();
        auth.setEmail("jane.doe@example.org");
        auth.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(auth);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"U@U.UUUU\",\"password\":\"iloveyou\",\"address\":\"42 Main"
                                        + " St\",\"gender\":\"MALE\",\"dateOfBirth\":\"1970-01-01\",\"age\":54,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(Integer, UserModel)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUser() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.JsonMappingException: Gender can be MALE/FEMALE/OTHER only (through reference chain: com.ecart.ecart.model.UserModel["modelValid"])
        //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:402)
        //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:361)
        //       at com.fasterxml.jackson.databind.ser.std.StdSerializer.wrapAndThrow(StdSerializer.java:323)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:780)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   com.ecart.ecart.exception.InvalidInputException: Gender can be MALE/FEMALE/OTHER only
        //       at com.ecart.ecart.model.UserModel.validateGender(UserModel.java:163)
        //       at com.ecart.ecart.model.UserModel.isModelValid(UserModel.java:89)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:688)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        UserModel userModel = new UserModel();
        userModel.setAddress("42 Main St");
        userModel.setAge(1L);
        userModel.setDateOfBirth("2020-03-01");
        userModel.setEmail("jane.doe@example.org");
        userModel.setGender("Gender");
        userModel.setPassword("iloveyou");
        userModel.setUserId(1);
        userModel.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/update/{userid}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
    }
}
