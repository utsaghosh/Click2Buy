package com.ecart.ecart.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Reviews;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.InvalidInputException;

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

@ContextConfiguration(classes = {ReviewModel.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ReviewModelDiffblueTest {
    @Autowired
    private ReviewModel reviewModel;

    /**
     * Method under test: {@link ReviewModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList() {
        // Arrange and Act
        List<ReviewModel> actualParseModelListFromEntityListResult = ReviewModel
                .parseModelListFromEntityList(new ArrayList<>());

        // Assert
        assertTrue(actualParseModelListFromEntityListResult.isEmpty());
    }

    /**
     * Method under test: {@link ReviewModel#parseModelListFromEntityList(List)}
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

        Reviews reviews = new Reviews();
        reviews.setBody("Not all who wander are lost");
        reviews.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        reviews.setItem(item);
        reviews.setReviewId(1);
        reviews.setUser(user);

        ArrayList<Reviews> reviews2 = new ArrayList<>();
        reviews2.add(reviews);

        // Act
        List<ReviewModel> actualParseModelListFromEntityListResult = ReviewModel.parseModelListFromEntityList(reviews2);

        // Assert
        assertEquals(1, actualParseModelListFromEntityListResult.size());
    }

    /**
     * Method under test: {@link ReviewModel#parseModelListFromEntityList(List)}
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

        Reviews reviews = new Reviews();
        reviews.setBody("Not all who wander are lost");
        reviews.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        reviews.setItem(item);
        reviews.setReviewId(1);
        reviews.setUser(user);

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

        Reviews reviews2 = new Reviews();
        reviews2.setBody("Body");
        reviews2.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        reviews2.setItem(item2);
        reviews2.setReviewId(2);
        reviews2.setUser(user2);

        ArrayList<Reviews> reviews3 = new ArrayList<>();
        reviews3.add(reviews2);
        reviews3.add(reviews);

        // Act
        List<ReviewModel> actualParseModelListFromEntityListResult = ReviewModel.parseModelListFromEntityList(reviews3);

        // Assert
        assertEquals(2, actualParseModelListFromEntityListResult.size());
    }

    /**
     * Method under test: {@link ReviewModel#parseModelListFromEntityList(List)}
     */
    @Test
    void testParseModelListFromEntityList4() {
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
        Reviews reviews = mock(Reviews.class);
        when(reviews.getBody()).thenReturn("Not all who wander are lost");
        when(reviews.getCreationDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(reviews.getUser()).thenReturn(users);
        when(reviews.getItem()).thenReturn(items);
        when(reviews.getReviewId()).thenReturn(1);
        doNothing().when(reviews).setBody(Mockito.<String>any());
        doNothing().when(reviews).setCreationDate(Mockito.<LocalDateTime>any());
        doNothing().when(reviews).setItem(Mockito.<Items>any());
        doNothing().when(reviews).setReviewId(Mockito.<Integer>any());
        doNothing().when(reviews).setUser(Mockito.<Users>any());
        reviews.setBody("Not all who wander are lost");
        reviews.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        reviews.setItem(item);
        reviews.setReviewId(1);
        reviews.setUser(user);

        ArrayList<Reviews> reviews2 = new ArrayList<>();
        reviews2.add(reviews);

        // Act
        List<ReviewModel> actualParseModelListFromEntityListResult = ReviewModel.parseModelListFromEntityList(reviews2);

        // Assert
        verify(reviews).getBody();
        verify(reviews).getCreationDate();
        verify(reviews).getItem();
        verify(reviews).getReviewId();
        verify(reviews, atLeast(1)).getUser();
        verify(reviews).setBody(eq("Not all who wander are lost"));
        verify(reviews).setCreationDate(isA(LocalDateTime.class));
        verify(reviews).setItem(isA(Items.class));
        verify(reviews).setReviewId(eq(1));
        verify(reviews).setUser(isA(Users.class));
        assertEquals(1, actualParseModelListFromEntityListResult.size());
    }

    /**
     * Method under test: {@link ReviewModel#isModelValid()}
     */
    @Test
    void testIsModelValid() throws InvalidInputException {
        // Arrange, Act and Assert
        assertThrows(InvalidInputException.class, () -> reviewModel.isModelValid());
    }

    /**
     * Method under test: {@link ReviewModel#ReviewModel(Reviews)}
     */
    @Test
    void testNewReviewModel() {
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

        Reviews review = new Reviews();
        review.setBody("Not all who wander are lost");
        review.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        review.setItem(item);
        review.setReviewId(1);
        review.setUser(user);

        // Act
        ReviewModel actualReviewModel = new ReviewModel(review);

        // Assert
        assertEquals("1970-01-01", actualReviewModel.getCreationDate().toLocalDate().toString());
        assertEquals("Not all who wander are lost", actualReviewModel.getBody());
        assertEquals("janedoe", actualReviewModel.getUserName());
        assertEquals(1, actualReviewModel.getItemId().intValue());
        assertEquals(1, actualReviewModel.getReviewId().intValue());
        assertEquals(1, actualReviewModel.getUserId().intValue());
    }

    /**
     * Method under test: {@link ReviewModel#ReviewModel(Reviews)}
     */
    @Test
    void testNewReviewModel2() {
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
        Reviews review = mock(Reviews.class);
        when(review.getBody()).thenReturn("Not all who wander are lost");
        when(review.getCreationDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(review.getUser()).thenReturn(users);
        when(review.getItem()).thenReturn(items);
        when(review.getReviewId()).thenReturn(1);
        doNothing().when(review).setBody(Mockito.<String>any());
        doNothing().when(review).setCreationDate(Mockito.<LocalDateTime>any());
        doNothing().when(review).setItem(Mockito.<Items>any());
        doNothing().when(review).setReviewId(Mockito.<Integer>any());
        doNothing().when(review).setUser(Mockito.<Users>any());
        review.setBody("Not all who wander are lost");
        review.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        review.setItem(item);
        review.setReviewId(1);
        review.setUser(user);

        // Act
        ReviewModel actualReviewModel = new ReviewModel(review);

        // Assert
        verify(review).getBody();
        verify(review).getCreationDate();
        verify(review).getItem();
        verify(review).getReviewId();
        verify(review, atLeast(1)).getUser();
        verify(review).setBody(eq("Not all who wander are lost"));
        verify(review).setCreationDate(isA(LocalDateTime.class));
        verify(review).setItem(isA(Items.class));
        verify(review).setReviewId(eq(1));
        verify(review).setUser(isA(Users.class));
        assertEquals("1970-01-01", actualReviewModel.getCreationDate().toLocalDate().toString());
        assertEquals("Not all who wander are lost", actualReviewModel.getBody());
        assertEquals("janedoe", actualReviewModel.getUserName());
        assertEquals(1, actualReviewModel.getItemId().intValue());
        assertEquals(1, actualReviewModel.getReviewId().intValue());
        assertEquals(1, actualReviewModel.getUserId().intValue());
    }
}
