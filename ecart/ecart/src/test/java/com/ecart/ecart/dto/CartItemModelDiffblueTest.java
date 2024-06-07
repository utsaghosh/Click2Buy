package com.ecart.ecart.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartItemModel.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CartItemModelDiffblueTest {
    @Autowired
    private CartItemModel cartItemModel;

    /**
     * Method under test: {@link CartItemModel#CartItemModel(CartItems)}
     */
    @Test
    void testNewCartItemModel() {
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

        CartItems cartItem = new CartItems();
        cartItem.setCartItemId(1);
        cartItem.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        cartItem.setItem(item);
        cartItem.setItemCount(3);
        cartItem.setItemPrice(42);
        cartItem.setUser(user);

        // Act
        CartItemModel actualCartItemModel = new CartItemModel(cartItem);

        // Assert
        assertEquals("1970-01-01", actualCartItemModel.getCreationDate().toLocalDate().toString());
        assertEquals("Item Image", actualCartItemModel.getItemImage());
        assertEquals("Item Name", actualCartItemModel.getItemName());
        assertEquals(0.0f, actualCartItemModel.getAverageRating().floatValue());
        assertEquals(1, actualCartItemModel.getCartItemId().intValue());
        assertEquals(1, actualCartItemModel.getItemId().intValue());
        assertEquals(1, actualCartItemModel.getUserId().intValue());
        assertEquals(3, actualCartItemModel.getItemCount().intValue());
        assertEquals(42, actualCartItemModel.getItemPrice().intValue());
    }

    /**
     * Method under test: {@link CartItemModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList() {
        // Arrange and Act
        List<CartItemModel> actualParseModelListFromEntityListResult = CartItemModel
                .parseModelListFromEntityList(new ArrayList<>());

        // Assert
        assertTrue(actualParseModelListFromEntityListResult.isEmpty());
    }

    /**
     * Method under test: {@link CartItemModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList2() {
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

        ArrayList<CartItems> cartItems2 = new ArrayList<>();
        cartItems2.add(cartItems);

        // Act
        List<CartItemModel> actualParseModelListFromEntityListResult = CartItemModel
                .parseModelListFromEntityList(cartItems2);

        // Assert
        assertEquals(1, actualParseModelListFromEntityListResult.size());
    }

    /**
     * Method under test: {@link CartItemModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList3() {
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
        item2.setItemDescription("42");
        item2.setItemId(2);
        item2.setItemImage("42");
        item2.setItemName("42");
        item2.setItemPrice(1);
        item2.setRatingsList(new ArrayList<>());
        item2.setReviewsList(new ArrayList<>());

        Users user2 = new Users();
        user2.setAddress("17 High St");
        user2.setAge(0L);
        user2.setCartEntriesList(new ArrayList<>());
        user2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        user2.setEmail("john.smith@example.org");
        user2.setGender(Gender.FEMALE);
        user2.setPassword("Password");
        user2.setRatingsList(new ArrayList<>());
        user2.setReviewsList(new ArrayList<>());
        user2.setUserId(2);
        user2.setUserName("User Name");

        CartItems cartItems2 = new CartItems();
        cartItems2.setCartItemId(2);
        cartItems2.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        cartItems2.setItem(item2);
        cartItems2.setItemCount(42);
        cartItems2.setItemPrice(1);
        cartItems2.setUser(user2);

        ArrayList<CartItems> cartItems3 = new ArrayList<>();
        cartItems3.add(cartItems2);
        cartItems3.add(cartItems);

        // Act
        List<CartItemModel> actualParseModelListFromEntityListResult = CartItemModel
                .parseModelListFromEntityList(cartItems3);

        // Assert
        assertEquals(2, actualParseModelListFromEntityListResult.size());
    }

    /**
     * Method under test: {@link CartItemModel#CartItemModel(CartItems)}
     */
    @Test
    void testNewCartItemModel2() {
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

        Items items = new Items();
        items.setCartEntriesList(new ArrayList<>());
        items.setItemDescription("Item Description");
        items.setItemId(1);
        items.setItemImage("Item Image");
        items.setItemName("Item Name");
        items.setItemPrice(42);
        items.setRatingsList(new ArrayList<>());
        items.setReviewsList(new ArrayList<>());

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
        CartItems cartItem = mock(CartItems.class);
        when(cartItem.getItemCount()).thenReturn(3);
        when(cartItem.getCreationDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(cartItem.getUser()).thenReturn(users);
        when(cartItem.getItem()).thenReturn(items);
        when(cartItem.getCartItemId()).thenReturn(1);
        doNothing().when(cartItem).setCartItemId(Mockito.<Integer>any());
        doNothing().when(cartItem).setCreationDate(Mockito.<LocalDateTime>any());
        doNothing().when(cartItem).setItem(Mockito.<Items>any());
        doNothing().when(cartItem).setItemCount(Mockito.<Integer>any());
        doNothing().when(cartItem).setItemPrice(Mockito.<Integer>any());
        doNothing().when(cartItem).setUser(Mockito.<Users>any());
        cartItem.setCartItemId(1);
        cartItem.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        cartItem.setItem(item);
        cartItem.setItemCount(3);
        cartItem.setItemPrice(42);
        cartItem.setUser(user);

        // Act
        CartItemModel actualCartItemModel = new CartItemModel(cartItem);

        // Assert
        verify(cartItem).getCartItemId();
        verify(cartItem).getCreationDate();
        verify(cartItem, atLeast(1)).getItem();
        verify(cartItem).getItemCount();
        verify(cartItem).getUser();
        verify(cartItem).setCartItemId(eq(1));
        verify(cartItem).setCreationDate(isA(LocalDateTime.class));
        verify(cartItem).setItem(isA(Items.class));
        verify(cartItem).setItemCount(eq(3));
        verify(cartItem).setItemPrice(eq(42));
        verify(cartItem).setUser(isA(Users.class));
        assertEquals("1970-01-01", actualCartItemModel.getCreationDate().toLocalDate().toString());
        assertEquals("Item Image", actualCartItemModel.getItemImage());
        assertEquals("Item Name", actualCartItemModel.getItemName());
        assertEquals(0.0f, actualCartItemModel.getAverageRating().floatValue());
        assertEquals(1, actualCartItemModel.getCartItemId().intValue());
        assertEquals(1, actualCartItemModel.getItemId().intValue());
        assertEquals(1, actualCartItemModel.getUserId().intValue());
        assertEquals(3, actualCartItemModel.getItemCount().intValue());
        assertEquals(42, actualCartItemModel.getItemPrice().intValue());
    }
}
