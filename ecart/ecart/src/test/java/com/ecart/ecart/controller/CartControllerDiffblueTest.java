package com.ecart.ecart.controller;

import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.dto.AddToCart;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.service.interfaces.CartItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CartControllerDiffblueTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartItemService cartItemService;

    /**
     * Method under test: {@link CartController#addToCart(AddToCart)}
     */
    @Test
    void testAddToCart() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Item Description");
        item.setItemId(1);
        item.setItemImage("Item Image");
        item.setItemName("Item Name");
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
        when(cartItemService.addToCart(Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(cartItems);

        AddToCart addToCart = new AddToCart();
        addToCart.setCount(3);
        addToCart.setItemId(1);
        addToCart.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(addToCart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/carts/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"cartItemId\":1,\"creationDate\":[1970,1,1,0,0],\"itemCount\":3,\"itemId\":1,\"itemName\":\"Item Name\",\"itemImage"
                                        + "\":\"Item Image\",\"itemPrice\":42,\"averageRating\":0.0,\"userId\":1}"));
    }

    /**
     * Method under test: {@link CartController#getCartItemId(Integer, Integer)}
     */
    @Test
    void testGetCartItemId() throws Exception {
        // Arrange
        when(cartItemService.getCartItemId(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/carts/getid/{itemid}/{userid}", 1, 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link CartController#getCartItemById(Integer)}
     */
    @Test
    void testGetCartItemById() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Item Description");
        item.setItemId(1);
        item.setItemImage("Item Image");
        item.setItemName("Item Name");
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
        when(cartItemService.getCartItemById(Mockito.<Integer>any())).thenReturn(cartItems);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/carts/getbyid/{cartitemid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"cartItemId\":1,\"creationDate\":[1970,1,1,0,0],\"itemCount\":3,\"itemId\":1,\"itemName\":\"Item Name\",\"itemImage"
                                        + "\":\"Item Image\",\"itemPrice\":42,\"averageRating\":0.0,\"userId\":1}"));
    }

    /**
     * Method under test: {@link CartController#getUserCart(Integer)}
     */
    @Test
    void testGetUserCart() throws Exception {
        // Arrange
        when(cartItemService.getUserCart(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/carts/getusercart/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CartController#getUserCart(Integer)}
     */
    @Test
    void testGetUserCart2() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Request cart of user={}");
        item.setItemId(1);
        item.setItemImage("Request cart of user={}");
        item.setItemName("Request cart of user={}");
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
        when(cartItemService.getUserCart(Mockito.<Integer>any())).thenReturn(cartItemsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/carts/getusercart/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"cartItemId\":1,\"creationDate\":[1970,1,1,0,0],\"itemCount\":3,\"itemId\":1,\"itemName\":\"Request cart of"
                                        + " user={}\",\"itemImage\":\"Request cart of user={}\",\"itemPrice\":42,\"averageRating\":0.0,\"userId\":1}]"));
    }

    /**
     * Method under test:
     * {@link CartController#checkItemInUserCart(Integer, Integer)}
     */
    @Test
    void testCheckItemInUserCart() throws Exception {
        // Arrange
        when(cartItemService.checkItemInUserCart(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/carts/check/{itemid}/{userid}", 1, 1);

        // Act and Assert
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link CartController#removeFromCart(Integer, Integer)}
     */
    @Test
    void testRemoveFromCart() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Item Description");
        item.setItemId(1);
        item.setItemImage("Item Image");
        item.setItemName("Item Name");
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
        when(cartItemService.removeFromCart(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(cartItems);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/carts/remove/{cartitemid}/{count}", 1,
                3);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"cartItemId\":1,\"creationDate\":[1970,1,1,0,0],\"itemCount\":3,\"itemId\":1,\"itemName\":\"Item Name\",\"itemImage"
                                        + "\":\"Item Image\",\"itemPrice\":42,\"averageRating\":0.0,\"userId\":1}"));
    }

    /**
     * Method under test: {@link CartController#delete(Integer)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Item Description");
        item.setItemId(1);
        item.setItemImage("Item Image");
        item.setItemName("Item Name");
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
        when(cartItemService.deleteCartItem(Mockito.<Integer>any())).thenReturn(cartItems);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/carts/delete/{cartitemid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"cartItemId\":1,\"creationDate\":[1970,1,1,0,0],\"itemCount\":3,\"itemId\":1,\"itemName\":\"Item Name\",\"itemImage"
                                        + "\":\"Item Image\",\"itemPrice\":42,\"averageRating\":0.0,\"userId\":1}"));
    }
}
