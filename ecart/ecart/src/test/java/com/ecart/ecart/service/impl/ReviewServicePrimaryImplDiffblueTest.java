package com.ecart.ecart.service.impl;

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

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Reviews;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.dto.ReviewModel;
import com.ecart.ecart.repository.ReviewsRepository;
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

@ContextConfiguration(classes = {ReviewServicePrimaryImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReviewServicePrimaryImplDiffblueTest {
    @MockBean
    private ItemService itemService;

    @Autowired
    private ReviewServicePrimaryImpl reviewServicePrimaryImpl;

    @MockBean
    private ReviewsRepository reviewsRepository;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link ReviewServicePrimaryImpl#addReview(ReviewModel)}
     */
    @Test
    void testAddReview() throws EntityAlreadyExistsException, EntityNotFoundException, InvalidInputException {
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
        when(reviewsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(reviews);

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
        assertThrows(EntityAlreadyExistsException.class, () -> reviewServicePrimaryImpl.addReview(
                new ReviewModel(1, 1, 1, "janedoe", "Not all who wander are lost", LocalDate.of(1970, 1, 1).atStartOfDay())));
        verify(reviewsRepository).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService).getItemById(eq(1));
        verify(userService).getUserById(eq(1));
    }

    /**
     * Method under test: {@link ReviewServicePrimaryImpl#getReviewById(Integer)}
     */
    @Test
    void testGetReviewById() throws EntityNotFoundException {
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
        Optional<Reviews> ofResult = Optional.of(reviews);
        when(reviewsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Reviews actualReviewById = reviewServicePrimaryImpl.getReviewById(1);

        // Assert
        verify(reviewsRepository).findById(eq(1));
        assertSame(reviews, actualReviewById);
    }

    /**
     * Method under test: {@link ReviewServicePrimaryImpl#getReviewById(Integer)}
     */
    @Test
    void testGetReviewById2() throws EntityNotFoundException {
        // Arrange
        Optional<Reviews> emptyResult = Optional.empty();
        when(reviewsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> reviewServicePrimaryImpl.getReviewById(1));
        verify(reviewsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link ReviewServicePrimaryImpl#getAllReviewsOfItem(Integer)}
     */
    @Test
    void testGetAllReviewsOfItem() throws EntityNotFoundException {
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
        ArrayList<Reviews> reviewsList = new ArrayList<>();
        when(reviewsRepository.findByItemId(Mockito.<Integer>any())).thenReturn(reviewsList);

        // Act
        List<Reviews> actualAllReviewsOfItem = reviewServicePrimaryImpl.getAllReviewsOfItem(1);

        // Assert
        verify(reviewsRepository).findByItemId(eq(1));
        verify(itemService).getItemById(eq(1));
        assertTrue(actualAllReviewsOfItem.isEmpty());
        assertSame(reviewsList, actualAllReviewsOfItem);
    }

    /**
     * Method under test:
     * {@link ReviewServicePrimaryImpl#getAllReviewsByUser(Integer)}
     */
    @Test
    void testGetAllReviewsByUser() throws EntityNotFoundException {
        // Arrange
        ArrayList<Reviews> reviewsList = new ArrayList<>();
        when(reviewsRepository.findByUserId(Mockito.<Integer>any())).thenReturn(reviewsList);

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
        List<Reviews> actualAllReviewsByUser = reviewServicePrimaryImpl.getAllReviewsByUser(1);

        // Assert
        verify(reviewsRepository).findByUserId(eq(1));
        verify(userService).getUserById(eq(1));
        assertTrue(actualAllReviewsByUser.isEmpty());
        assertSame(reviewsList, actualAllReviewsByUser);
    }

    /**
     * Method under test:
     * {@link ReviewServicePrimaryImpl#getReviewOfItemByUser(Integer, Integer)}
     */
    @Test
    void testGetReviewOfItemByUser() throws EntityNotFoundException {
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
        when(reviewsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(reviews);

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
        Reviews actualReviewOfItemByUser = reviewServicePrimaryImpl.getReviewOfItemByUser(1, 1);

        // Assert
        verify(reviewsRepository).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService).getItemById(eq(1));
        verify(userService).getUserById(eq(1));
        assertSame(reviews, actualReviewOfItemByUser);
    }

    /**
     * Method under test:
     * {@link ReviewServicePrimaryImpl#updateReview(Integer, ReviewModel)}
     */
    @Test
    void testUpdateReview() throws EntityNotFoundException, InvalidInputException {
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
        Optional<Reviews> ofResult = Optional.of(reviews);
        when(reviewsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> reviewServicePrimaryImpl.updateReview(1, new ReviewModel()));
        verify(reviewsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link ReviewServicePrimaryImpl#updateReview(Integer, ReviewModel)}
     */
    @Test
    void testUpdateReview2() throws EntityNotFoundException, InvalidInputException {
        // Arrange
        Optional<Reviews> emptyResult = Optional.empty();
        when(reviewsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> reviewServicePrimaryImpl.updateReview(1, new ReviewModel()));
        verify(reviewsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link ReviewServicePrimaryImpl#updateReview(Integer, ReviewModel)}
     */
    @Test
    void testUpdateReview3() throws EntityNotFoundException, InvalidInputException {
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

        Items items = new Items();
        items.setCartEntriesList(new ArrayList<>());
        items.setItemDescription("Item Description");
        items.setItemId(1);
        items.setItemImage("Item Image");
        items.setItemName("Item Name");
        items.setItemPrice(42);
        items.setRatingsList(new ArrayList<>());
        items.setReviewsList(new ArrayList<>());
        Reviews reviews = mock(Reviews.class);
        when(reviews.getItem()).thenReturn(items);
        when(reviews.getUser()).thenReturn(users);
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
        Optional<Reviews> ofResult = Optional.of(reviews);

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

        Reviews reviews2 = new Reviews();
        reviews2.setBody("Not all who wander are lost");
        reviews2.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        reviews2.setItem(item2);
        reviews2.setReviewId(1);
        reviews2.setUser(user2);
        when(reviewsRepository.save(Mockito.<Reviews>any())).thenReturn(reviews2);
        when(reviewsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Reviews actualUpdateReviewResult = reviewServicePrimaryImpl.updateReview(1,
                new ReviewModel(1, 1, 1, "janedoe", "Not all who wander are lost", LocalDate.of(1970, 1, 1).atStartOfDay()));

        // Assert
        verify(reviews).getItem();
        verify(reviews).getUser();
        verify(reviews, atLeast(1)).setBody(eq("Not all who wander are lost"));
        verify(reviews, atLeast(1)).setCreationDate(isA(LocalDateTime.class));
        verify(reviews).setItem(isA(Items.class));
        verify(reviews).setReviewId(eq(1));
        verify(reviews).setUser(isA(Users.class));
        verify(reviewsRepository).findById(eq(1));
        verify(reviewsRepository).save(isA(Reviews.class));
        assertSame(reviews2, actualUpdateReviewResult);
    }

    /**
     * Method under test: {@link ReviewServicePrimaryImpl#deleteReview(Integer)}
     */
    @Test
    void testDeleteReview() throws EntityNotFoundException {
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
        Optional<Reviews> ofResult = Optional.of(reviews);
        doNothing().when(reviewsRepository).deleteById(Mockito.<Integer>any());
        when(reviewsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Reviews actualDeleteReviewResult = reviewServicePrimaryImpl.deleteReview(1);

        // Assert
        verify(reviewsRepository).deleteById(eq(1));
        verify(reviewsRepository).findById(eq(1));
        assertSame(reviews, actualDeleteReviewResult);
    }

    /**
     * Method under test: {@link ReviewServicePrimaryImpl#deleteReview(Integer)}
     */
    @Test
    void testDeleteReview2() throws EntityNotFoundException {
        // Arrange
        Optional<Reviews> emptyResult = Optional.empty();
        when(reviewsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> reviewServicePrimaryImpl.deleteReview(1));
        verify(reviewsRepository).findById(eq(1));
    }
}
