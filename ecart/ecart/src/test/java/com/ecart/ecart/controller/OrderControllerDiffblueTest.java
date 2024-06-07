package com.ecart.ecart.controller;

import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.OrderItems;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.service.interfaces.OrderItemService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OrderControllerDiffblueTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderItemService orderItemService;

    /**
     * Method under test: {@link OrderController#placeOrder(Integer)}
     */
    @Test
    void testPlaceOrder() throws Exception {
        // Arrange
        when(orderItemService.placeOrder(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/place/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#placeOrder(Integer)}
     */
    @Test
    void testPlaceOrder2() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Request to place order for cart of user={}");
        item.setItemId(1);
        item.setItemImage("Request to place order for cart of user={}");
        item.setItemName("Request to place order for cart of user={}");
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

        OrderItems orderItems = new OrderItems();
        orderItems.setAddress("42 Main St");
        orderItems.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        orderItems.setItem(item);
        orderItems.setItemCount(3);
        orderItems.setOrderItemId(1);
        orderItems.setOrderPrice(1);
        orderItems.setUser(user);

        ArrayList<OrderItems> orderItemsList = new ArrayList<>();
        orderItemsList.add(orderItems);
        when(orderItemService.placeOrder(Mockito.<Integer>any())).thenReturn(orderItemsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/place/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"orderItemId\":1,\"orderPrice\":1,\"itemCount\":3,\"creationDate\":[1970,1,1,0,0],\"itemId\":1,\"itemName\":\"Request"
                                        + " to place order for cart of user={}\",\"itemImage\":\"Request to place order for cart of user={}\",\"userId"
                                        + "\":1,\"address\":\"42 Main St\"}]"));
    }

    /**
     * Method under test: {@link OrderController#cancelorder(Integer)}
     */
    @Test
    void testCancelorder() throws Exception {
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

        OrderItems orderItems = new OrderItems();
        orderItems.setAddress("42 Main St");
        orderItems.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        orderItems.setItem(item);
        orderItems.setItemCount(3);
        orderItems.setOrderItemId(1);
        orderItems.setOrderPrice(1);
        orderItems.setUser(user);
        when(orderItemService.cancelOrder(Mockito.<Integer>any())).thenReturn(orderItems);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/cancel/{orderid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"orderItemId\":1,\"orderPrice\":1,\"itemCount\":3,\"creationDate\":[1970,1,1,0,0],\"itemId\":1,\"itemName\":\"Item"
                                        + " Name\",\"itemImage\":\"Item Image\",\"userId\":1,\"address\":\"42 Main St\"}"));
    }

    /**
     * Method under test:
     * {@link OrderController#checkItemOrderedByUser(Integer, Integer)}
     */
    @Test
    void testCheckItemOrderedByUser() throws Exception {
        // Arrange
        when(orderItemService.checkItemOrderedByUser(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/check/{itemid}/{userid}", 1, 1);

        // Act and Assert
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link OrderController#getOrdersByUser(Integer)}
     */
    @Test
    void testGetOrdersByUser() throws Exception {
        // Arrange
        when(orderItemService.getOrdersByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/getbyuser/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#getOrdersByUser(Integer)}
     */
    @Test
    void testGetOrdersByUser2() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Request to get all orders by user={}");
        item.setItemId(1);
        item.setItemImage("Request to get all orders by user={}");
        item.setItemName("Request to get all orders by user={}");
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

        OrderItems orderItems = new OrderItems();
        orderItems.setAddress("42 Main St");
        orderItems.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        orderItems.setItem(item);
        orderItems.setItemCount(3);
        orderItems.setOrderItemId(1);
        orderItems.setOrderPrice(1);
        orderItems.setUser(user);

        ArrayList<OrderItems> orderItemsList = new ArrayList<>();
        orderItemsList.add(orderItems);
        when(orderItemService.getOrdersByUser(Mockito.<Integer>any())).thenReturn(orderItemsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/getbyuser/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"orderItemId\":1,\"orderPrice\":1,\"itemCount\":3,\"creationDate\":[1970,1,1,0,0],\"itemId\":1,\"itemName\":\"Request"
                                        + " to get all orders by user={}\",\"itemImage\":\"Request to get all orders by user={}\",\"userId\":1,\"address\":\"42"
                                        + " Main St\"}]"));
    }
}
