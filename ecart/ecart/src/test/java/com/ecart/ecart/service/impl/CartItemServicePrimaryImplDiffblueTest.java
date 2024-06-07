package com.ecart.ecart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.repository.CartItemsRepository;
import com.ecart.ecart.service.interfaces.ItemService;
import com.ecart.ecart.service.interfaces.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartItemServicePrimaryImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CartItemServicePrimaryImplDiffblueTest {
    @Autowired
    private CartItemServicePrimaryImpl cartItemServicePrimaryImpl;

    @MockBean
    private CartItemsRepository cartItemsRepository;

    @MockBean
    private ItemService itemService;

    @MockBean
    private UserService userService;

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#addToCart(Integer, Integer, Integer)}
     */
    @Test
    void testAddToCart() throws EntityAlreadyExistsException, EntityNotFoundException, InvalidInputException {
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

        CartItems cartItems3 = new CartItems();
        cartItems3.setCartItemId(1);
        cartItems3.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        cartItems3.setItem(item3);
        cartItems3.setItemCount(3);
        cartItems3.setItemPrice(42);
        cartItems3.setUser(user3);
        Optional<CartItems> ofResult = Optional.of(cartItems3);
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(cartItemsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(cartItems);
        when(cartItemsRepository.save(Mockito.<CartItems>any())).thenReturn(cartItems2);

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
        CartItems actualAddToCartResult = cartItemServicePrimaryImpl.addToCart(1, 1, 3);

        // Assert
        verify(cartItemsRepository, atLeast(1)).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService, atLeast(1)).getItemById(eq(1));
        verify(userService, atLeast(1)).getUserById(eq(1));
        verify(cartItemsRepository).findById(eq(1));
        verify(cartItemsRepository).save(isA(CartItems.class));
        assertSame(cartItems2, actualAddToCartResult);
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#addToCart(Integer, Integer, Integer)}
     */
    @Test
    void testAddToCart2() throws EntityAlreadyExistsException, EntityNotFoundException, InvalidInputException {
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
        Optional<CartItems> emptyResult = Optional.empty();
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        when(cartItemsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(cartItems);

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

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> cartItemServicePrimaryImpl.addToCart(1, 1, 3));
        verify(cartItemsRepository, atLeast(1)).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService, atLeast(1)).getItemById(eq(1));
        verify(userService, atLeast(1)).getUserById(eq(1));
        verify(cartItemsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#getCartItemId(Integer, Integer)}
     */
    @Test
    void testGetCartItemId() throws EntityNotFoundException {
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
        user.setAge(5L);
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
        when(cartItemsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(cartItems);

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

        Users users = new Users();
        users.setAddress("42 Main St");
        users.setAge(5L);
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
        Integer actualCartItemId = cartItemServicePrimaryImpl.getCartItemId(1, 1);

        // Assert
        verify(cartItemsRepository).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService).getItemById(eq(1));
        verify(userService).getUserById(eq(1));
        assertEquals(1, actualCartItemId.intValue());
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#getCartItemById(Integer)}
     */
    @Test
    void testGetCartItemById() throws EntityNotFoundException {
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
        Optional<CartItems> ofResult = Optional.of(cartItems);
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        CartItems actualCartItemById = cartItemServicePrimaryImpl.getCartItemById(1);

        // Assert
        verify(cartItemsRepository).findById(eq(1));
        assertSame(cartItems, actualCartItemById);
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#getCartItemById(Integer)}
     */
    @Test
    void testGetCartItemById2() throws EntityNotFoundException {
        // Arrange
        Optional<CartItems> emptyResult = Optional.empty();
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> cartItemServicePrimaryImpl.getCartItemById(1));
        verify(cartItemsRepository).findById(eq(1));
    }

    /**
     * Method under test: {@link CartItemServicePrimaryImpl#getUserCart(Integer)}
     */
    @Test
    void testGetUserCart() throws EntityNotFoundException {
        // Arrange
        ArrayList<CartItems> cartItemsList = new ArrayList<>();
        when(cartItemsRepository.findByUserId(Mockito.<Integer>any())).thenReturn(cartItemsList);

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
        List<CartItems> actualUserCart = cartItemServicePrimaryImpl.getUserCart(1);

        // Assert
        verify(cartItemsRepository).findByUserId(eq(1));
        verify(userService).getUserById(eq(1));
        assertTrue(actualUserCart.isEmpty());
        assertSame(cartItemsList, actualUserCart);
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#checkItemInUserCart(Integer, Integer)}
     */
    @Test
    void testCheckItemInUserCart() throws EntityNotFoundException {
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
        when(cartItemsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(cartItems);

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
        Boolean actualCheckItemInUserCartResult = cartItemServicePrimaryImpl.checkItemInUserCart(1, 1);

        // Assert
        verify(cartItemsRepository).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService, atLeast(1)).getItemById(eq(1));
        verify(userService, atLeast(1)).getUserById(eq(1));
        assertTrue(actualCheckItemInUserCartResult);
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#removeFromCart(Integer, Integer)}
     */
    @Test
    void testRemoveFromCart() throws EntityNotFoundException, InvalidInputException {
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
        Optional<CartItems> ofResult = Optional.of(cartItems);
        doNothing().when(cartItemsRepository).deleteById(Mockito.<Integer>any());
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        CartItems actualRemoveFromCartResult = cartItemServicePrimaryImpl.removeFromCart(1, 3);

        // Assert
        verify(cartItemsRepository).deleteById(eq(1));
        verify(cartItemsRepository, atLeast(1)).findById(eq(1));
        assertSame(cartItems, actualRemoveFromCartResult);
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#removeFromCart(Integer, Integer)}
     */
    @Test
    void testRemoveFromCart2() throws EntityNotFoundException, InvalidInputException {
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
        CartItems cartItems = mock(CartItems.class);
        when(cartItems.getItemCount()).thenReturn(1);
        doNothing().when(cartItems).setCartItemId(Mockito.<Integer>any());
        doNothing().when(cartItems).setCreationDate(Mockito.<LocalDateTime>any());
        doNothing().when(cartItems).setItem(Mockito.<Items>any());
        doNothing().when(cartItems).setItemCount(Mockito.<Integer>any());
        doNothing().when(cartItems).setItemPrice(Mockito.<Integer>any());
        doNothing().when(cartItems).setUser(Mockito.<Users>any());
        cartItems.setCartItemId(1);
        cartItems.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        cartItems.setItem(item);
        cartItems.setItemCount(3);
        cartItems.setItemPrice(42);
        cartItems.setUser(user);
        Optional<CartItems> ofResult = Optional.of(cartItems);
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(InvalidInputException.class, () -> cartItemServicePrimaryImpl.removeFromCart(1, 3));
        verify(cartItems).getItemCount();
        verify(cartItems).setCartItemId(eq(1));
        verify(cartItems).setCreationDate(isA(LocalDateTime.class));
        verify(cartItems).setItem(isA(Items.class));
        verify(cartItems).setItemCount(eq(3));
        verify(cartItems).setItemPrice(eq(42));
        verify(cartItems).setUser(isA(Users.class));
        verify(cartItemsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#removeFromCart(Integer, Integer)}
     */
    @Test
    void testRemoveFromCart3() throws EntityNotFoundException, InvalidInputException {
        // Arrange
        Optional<CartItems> emptyResult = Optional.empty();
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> cartItemServicePrimaryImpl.removeFromCart(1, 3));
        verify(cartItemsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link CartItemServicePrimaryImpl#removeFromCart(Integer, Integer)}
     */
    @Test
    void testRemoveFromCart4() throws EntityNotFoundException, InvalidInputException {
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
        CartItems cartItems = mock(CartItems.class);
        when(cartItems.getItemCount()).thenReturn(3);
        doNothing().when(cartItems).setCartItemId(Mockito.<Integer>any());
        doNothing().when(cartItems).setCreationDate(Mockito.<LocalDateTime>any());
        doNothing().when(cartItems).setItem(Mockito.<Items>any());
        doNothing().when(cartItems).setItemCount(Mockito.<Integer>any());
        doNothing().when(cartItems).setItemPrice(Mockito.<Integer>any());
        doNothing().when(cartItems).setUser(Mockito.<Users>any());
        cartItems.setCartItemId(1);
        cartItems.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        cartItems.setItem(item);
        cartItems.setItemCount(3);
        cartItems.setItemPrice(42);
        cartItems.setUser(user);
        Optional<CartItems> ofResult = Optional.of(cartItems);

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
        when(cartItemsRepository.save(Mockito.<CartItems>any())).thenReturn(cartItems2);
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        CartItems actualRemoveFromCartResult = cartItemServicePrimaryImpl.removeFromCart(1, 1);

        // Assert
        verify(cartItems, atLeast(1)).getItemCount();
        verify(cartItems).setCartItemId(eq(1));
        verify(cartItems, atLeast(1)).setCreationDate(isA(LocalDateTime.class));
        verify(cartItems).setItem(isA(Items.class));
        verify(cartItems, atLeast(1)).setItemCount(Mockito.<Integer>any());
        verify(cartItems).setItemPrice(eq(42));
        verify(cartItems).setUser(isA(Users.class));
        verify(cartItemsRepository).findById(eq(1));
        verify(cartItemsRepository).save(isA(CartItems.class));
        assertSame(cartItems2, actualRemoveFromCartResult);
    }

    /**
     * Method under test: {@link CartItemServicePrimaryImpl#deleteCartItem(Integer)}
     */
    @Test
    void testDeleteCartItem() throws EntityNotFoundException {
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
        Optional<CartItems> ofResult = Optional.of(cartItems);
        doNothing().when(cartItemsRepository).deleteById(Mockito.<Integer>any());
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        CartItems actualDeleteCartItemResult = cartItemServicePrimaryImpl.deleteCartItem(1);

        // Assert
        verify(cartItemsRepository).deleteById(eq(1));
        verify(cartItemsRepository).findById(eq(1));
        assertSame(cartItems, actualDeleteCartItemResult);
    }

    /**
     * Method under test: {@link CartItemServicePrimaryImpl#deleteCartItem(Integer)}
     */
    @Test
    void testDeleteCartItem2() throws EntityNotFoundException {
        // Arrange
        Optional<CartItems> emptyResult = Optional.empty();
        when(cartItemsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> cartItemServicePrimaryImpl.deleteCartItem(1));
        verify(cartItemsRepository).findById(eq(1));
    }
}
