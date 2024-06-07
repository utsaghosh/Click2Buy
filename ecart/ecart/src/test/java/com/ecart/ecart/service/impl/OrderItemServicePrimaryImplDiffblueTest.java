package com.ecart.ecart.service.impl;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.OrderItems;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.repository.OrderItemsRepository;
import com.ecart.ecart.service.interfaces.CartItemService;
import com.ecart.ecart.service.interfaces.ItemService;
import com.ecart.ecart.service.interfaces.UserService;
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
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderItemServicePrimaryImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OrderItemServicePrimaryImplDiffblueTest {
    @MockBean
    private CartItemService cartItemService;

    @MockBean
    private ItemService itemService;

    @Autowired
    private OrderItemServicePrimaryImpl orderItemServicePrimaryImpl;

    @MockBean
    private OrderItemsRepository orderItemsRepository;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link OrderItemServicePrimaryImpl#placeOrder(Integer)}
     */
    @Test
    void testPlaceOrder() throws EntityNotFoundException {
        // Arrange
        when(cartItemService.getUserCart(Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        // Act
        List<OrderItems> actualPlaceOrderResult = orderItemServicePrimaryImpl.placeOrder(1);

        // Assert
        verify(cartItemService).getUserCart(eq(1));
        assertTrue(actualPlaceOrderResult.isEmpty());
    }

    /**
     * Method under test: {@link OrderItemServicePrimaryImpl#placeOrder(Integer)}
     */
    @Test
    void testPlaceOrder2() throws EntityNotFoundException {
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

        ArrayList<CartItems> cartItemsList = new ArrayList<>();
        cartItemsList.add(cartItems);

        Items item2 = new Items();
        item2.setCartEntriesList(new ArrayList<>());
        item2.setItemDescription("Item Description");
        item2.setItemId(1);
        item2.setItemImage("Item Image");
        item2.setItemName("Item Name");
        item2.setItemPrice(42);
        item2.setRatingsList(new ArrayList<>());
        item2.setReviewsList(new ArrayList<>());

        Users user2 = new Users();
        user2.setAddress("42 Main St");
        user2.setAge(1L);
        user2.setCartEntriesList(new ArrayList<>());
        user2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        user2.setEmail("jane.doe@example.org");
        user2.setGender(Gender.MALE);
        user2.setPassword("iloveyou");
        user2.setRatingsList(new ArrayList<>());
        user2.setReviewsList(new ArrayList<>());
        user2.setUserId(1);
        user2.setUserName("janedoe");

        CartItems cartItems2 = new CartItems();
        cartItems2.setCartItemId(1);
        cartItems2.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        cartItems2.setItem(item2);
        cartItems2.setItemCount(3);
        cartItems2.setItemPrice(42);
        cartItems2.setUser(user2);
        when(cartItemService.deleteCartItem(Mockito.<Integer>any())).thenReturn(cartItems2);
        when(cartItemService.getUserCart(Mockito.<Integer>any())).thenReturn(cartItemsList);

        Items item3 = new Items();
        item3.setCartEntriesList(new ArrayList<>());
        item3.setItemDescription("Item Description");
        item3.setItemId(1);
        item3.setItemImage("Item Image");
        item3.setItemName("Item Name");
        item3.setItemPrice(42);
        item3.setRatingsList(new ArrayList<>());
        item3.setReviewsList(new ArrayList<>());

        Users user3 = new Users();
        user3.setAddress("42 Main St");
        user3.setAge(1L);
        user3.setCartEntriesList(new ArrayList<>());
        user3.setDateOfBirth(LocalDate.of(1970, 1, 1));
        user3.setEmail("jane.doe@example.org");
        user3.setGender(Gender.MALE);
        user3.setPassword("iloveyou");
        user3.setRatingsList(new ArrayList<>());
        user3.setReviewsList(new ArrayList<>());
        user3.setUserId(1);
        user3.setUserName("janedoe");

        OrderItems orderItems = new OrderItems();
        orderItems.setAddress("42 Main St");
        orderItems.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        orderItems.setItem(item3);
        orderItems.setItemCount(3);
        orderItems.setOrderItemId(1);
        orderItems.setOrderPrice(1);
        orderItems.setUser(user3);
        when(orderItemsRepository.save(Mockito.<OrderItems>any())).thenReturn(orderItems);

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

        // Act
        List<OrderItems> actualPlaceOrderResult = orderItemServicePrimaryImpl.placeOrder(1);

        // Assert
        verify(cartItemService).deleteCartItem(eq(1));
        verify(cartItemService).getUserCart(eq(1));
        verify(userService).getUserById(eq(1));
        verify(orderItemsRepository).save(isA(OrderItems.class));
        assertEquals(1, actualPlaceOrderResult.size());
    }

    /**
     * Method under test: {@link OrderItemServicePrimaryImpl#cancelOrder(Integer)}
     */
    @Test
    void testCancelOrder() throws EntityNotFoundException, InvalidInputException {
        // Arrange
        Optional<OrderItems> emptyResult = Optional.empty();
        when(orderItemsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> orderItemServicePrimaryImpl.cancelOrder(1));
        verify(orderItemsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link OrderItemServicePrimaryImpl#getOrdersByUser(Integer)}
     */
    @Test
    void testGetOrdersByUser() throws EntityNotFoundException {
        // Arrange
        ArrayList<OrderItems> orderItemsList = new ArrayList<>();
        when(orderItemsRepository.findByUserId(Mockito.<Integer>any())).thenReturn(orderItemsList);

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

        // Act
        List<OrderItems> actualOrdersByUser = orderItemServicePrimaryImpl.getOrdersByUser(1);

        // Assert
        verify(orderItemsRepository).findByUserId(eq(1));
        verify(userService).getUserById(eq(1));
        assertTrue(actualOrdersByUser.isEmpty());
        assertSame(orderItemsList, actualOrdersByUser);
    }

    /**
     * Method under test:
     * {@link OrderItemServicePrimaryImpl#checkItemOrderedByUser(Integer, Integer)}
     */
    @Test
    void testCheckItemOrderedByUser() throws EntityNotFoundException {
        // Arrange
        Items items = new Items();
        items.setCartEntriesList(new ArrayList<>());
        items.setItemDescription("Item Description");
        items.setItemId(1);
        items.setItemImage("Item Image");
        items.setItemName("Item Name");
        items.setItemPrice(42);
        items.setRatingsList(new ArrayList<>());
        items.setReviewsList(new ArrayList<>());
        when(itemService.getItemById(Mockito.<Integer>any())).thenReturn(items);
        when(orderItemsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(new ArrayList<>());

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

        // Act
        Boolean actualCheckItemOrderedByUserResult = orderItemServicePrimaryImpl.checkItemOrderedByUser(1, 1);

        // Assert
        verify(orderItemsRepository).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService).getItemById(eq(1));
        verify(userService).getUserById(eq(1));
        assertFalse(actualCheckItemOrderedByUserResult);
    }

    /**
     * Method under test:
     * {@link OrderItemServicePrimaryImpl#checkItemOrderedByUser(Integer, Integer)}
     */
    @Test
    void testCheckItemOrderedByUser2() throws EntityNotFoundException {
        // Arrange
        Items items = new Items();
        items.setCartEntriesList(new ArrayList<>());
        items.setItemDescription("Item Description");
        items.setItemId(1);
        items.setItemImage("Item Image");
        items.setItemName("Item Name");
        items.setItemPrice(42);
        items.setRatingsList(new ArrayList<>());
        items.setReviewsList(new ArrayList<>());
        when(itemService.getItemById(Mockito.<Integer>any())).thenReturn(items);

        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Item Found");
        item.setItemId(1);
        item.setItemImage("Item Found");
        item.setItemName("Item Found");
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
        when(orderItemsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(orderItemsList);

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

        // Act
        Boolean actualCheckItemOrderedByUserResult = orderItemServicePrimaryImpl.checkItemOrderedByUser(1, 1);

        // Assert
        verify(orderItemsRepository).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService).getItemById(eq(1));
        verify(userService).getUserById(eq(1));
        assertTrue(actualCheckItemOrderedByUserResult);
    }
}
