package com.ecart.ecart.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Ratings;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.dto.RatingModel;
import com.ecart.ecart.repository.RatingsRepository;
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

@ContextConfiguration(classes = {RatingServicePrimaryImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RatingServicePrimaryImplDiffblueTest {
    @MockBean
    private ItemService itemService;

    @Autowired
    private RatingServicePrimaryImpl ratingServicePrimaryImpl;

    @MockBean
    private RatingsRepository ratingsRepository;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link RatingServicePrimaryImpl#addRating(RatingModel)}
     */
    @Test
    void testAddRating() throws EntityAlreadyExistsException, EntityNotFoundException, InvalidInputException {
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

        Ratings ratings = new Ratings();
        ratings.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratings.setItem(item);
        ratings.setRatingId(1);
        ratings.setScore(3);
        ratings.setUser(user);
        when(ratingsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(ratings);

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
        assertThrows(EntityAlreadyExistsException.class, () -> ratingServicePrimaryImpl.addRating(new RatingModel()));
        verify(ratingsRepository).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService).getItemById(isNull());
        verify(userService).getUserById(isNull());
    }

    /**
     * Method under test: {@link RatingServicePrimaryImpl#getRatingById(Integer)}
     */
    @Test
    void testGetRatingById() throws EntityNotFoundException {
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

        Ratings ratings = new Ratings();
        ratings.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratings.setItem(item);
        ratings.setRatingId(1);
        ratings.setScore(3);
        ratings.setUser(user);
        Optional<Ratings> ofResult = Optional.of(ratings);
        when(ratingsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Ratings actualRatingById = ratingServicePrimaryImpl.getRatingById(1);

        // Assert
        verify(ratingsRepository).findById(eq(1));
        assertSame(ratings, actualRatingById);
    }

    /**
     * Method under test: {@link RatingServicePrimaryImpl#getRatingById(Integer)}
     */
    @Test
    void testGetRatingById2() throws EntityNotFoundException {
        // Arrange
        Optional<Ratings> emptyResult = Optional.empty();
        when(ratingsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> ratingServicePrimaryImpl.getRatingById(1));
        verify(ratingsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link RatingServicePrimaryImpl#getAllRatingsOfItem(Integer)}
     */
    @Test
    void testGetAllRatingsOfItem() throws EntityNotFoundException {
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
        ArrayList<Ratings> ratingsList = new ArrayList<>();
        when(ratingsRepository.findByItemId(Mockito.<Integer>any())).thenReturn(ratingsList);

        // Act
        List<Ratings> actualAllRatingsOfItem = ratingServicePrimaryImpl.getAllRatingsOfItem(1);

        // Assert
        verify(ratingsRepository).findByItemId(eq(1));
        verify(itemService).getItemById(eq(1));
        assertTrue(actualAllRatingsOfItem.isEmpty());
        assertSame(ratingsList, actualAllRatingsOfItem);
    }

    /**
     * Method under test:
     * {@link RatingServicePrimaryImpl#getAllRatingsByUser(Integer)}
     */
    @Test
    void testGetAllRatingsByUser() throws EntityNotFoundException {
        // Arrange
        ArrayList<Ratings> ratingsList = new ArrayList<>();
        when(ratingsRepository.findByUserId(Mockito.<Integer>any())).thenReturn(ratingsList);

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
        List<Ratings> actualAllRatingsByUser = ratingServicePrimaryImpl.getAllRatingsByUser(1);

        // Assert
        verify(ratingsRepository).findByUserId(eq(1));
        verify(userService).getUserById(eq(1));
        assertTrue(actualAllRatingsByUser.isEmpty());
        assertSame(ratingsList, actualAllRatingsByUser);
    }

    /**
     * Method under test:
     * {@link RatingServicePrimaryImpl#getRatingOfItemByUser(Integer, Integer)}
     */
    @Test
    void testGetRatingOfItemByUser() throws EntityNotFoundException {
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

        Ratings ratings = new Ratings();
        ratings.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratings.setItem(item);
        ratings.setRatingId(1);
        ratings.setScore(3);
        ratings.setUser(user);
        when(ratingsRepository.findByItemIdAndUserId(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(ratings);

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
        Ratings actualRatingOfItemByUser = ratingServicePrimaryImpl.getRatingOfItemByUser(1, 1);

        // Assert
        verify(ratingsRepository).findByItemIdAndUserId(eq(1), eq(1));
        verify(itemService).getItemById(eq(1));
        verify(userService).getUserById(eq(1));
        assertSame(ratings, actualRatingOfItemByUser);
    }

    /**
     * Method under test:
     * {@link RatingServicePrimaryImpl#updateRating(Integer, RatingModel)}
     */
    @Test
    void testUpdateRating() throws EntityNotFoundException, InvalidInputException {
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

        Ratings ratings = new Ratings();
        ratings.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratings.setItem(item);
        ratings.setRatingId(1);
        ratings.setScore(3);
        ratings.setUser(user);
        Optional<Ratings> ofResult = Optional.of(ratings);
        when(ratingsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> ratingServicePrimaryImpl.updateRating(1, new RatingModel()));
        verify(ratingsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link RatingServicePrimaryImpl#updateRating(Integer, RatingModel)}
     */
    @Test
    void testUpdateRating2() throws EntityNotFoundException, InvalidInputException {
        // Arrange
        Optional<Ratings> emptyResult = Optional.empty();
        when(ratingsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> ratingServicePrimaryImpl.updateRating(1, new RatingModel()));
        verify(ratingsRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link RatingServicePrimaryImpl#updateRating(Integer, RatingModel)}
     */
    @Test
    void testUpdateRating3() throws EntityNotFoundException, InvalidInputException {
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
        Ratings ratings = mock(Ratings.class);
        when(ratings.getItem()).thenReturn(items);
        when(ratings.getUser()).thenReturn(users);
        doNothing().when(ratings).setCreationDate(Mockito.<LocalDateTime>any());
        doNothing().when(ratings).setItem(Mockito.<Items>any());
        doNothing().when(ratings).setRatingId(Mockito.<Integer>any());
        doNothing().when(ratings).setScore(Mockito.<Integer>any());
        doNothing().when(ratings).setUser(Mockito.<Users>any());
        ratings.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratings.setItem(item);
        ratings.setRatingId(1);
        ratings.setScore(3);
        ratings.setUser(user);
        Optional<Ratings> ofResult = Optional.of(ratings);

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

        Ratings ratings2 = new Ratings();
        ratings2.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratings2.setItem(item2);
        ratings2.setRatingId(1);
        ratings2.setScore(3);
        ratings2.setUser(user2);
        when(ratingsRepository.save(Mockito.<Ratings>any())).thenReturn(ratings2);
        when(ratingsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Ratings actualUpdateRatingResult = ratingServicePrimaryImpl.updateRating(1,
                new RatingModel(1, 1, 1, "janedoe", 3, LocalDate.of(1970, 1, 1).atStartOfDay()));

        // Assert
        verify(ratings).getItem();
        verify(ratings).getUser();
        verify(ratings, atLeast(1)).setCreationDate(isA(LocalDateTime.class));
        verify(ratings).setItem(isA(Items.class));
        verify(ratings).setRatingId(eq(1));
        verify(ratings, atLeast(1)).setScore(eq(3));
        verify(ratings).setUser(isA(Users.class));
        verify(ratingsRepository).findById(eq(1));
        verify(ratingsRepository).save(isA(Ratings.class));
        assertSame(ratings2, actualUpdateRatingResult);
    }

    /**
     * Method under test: {@link RatingServicePrimaryImpl#deleteRating(Integer)}
     */
    @Test
    void testDeleteRating() throws EntityNotFoundException {
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

        Ratings ratings = new Ratings();
        ratings.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratings.setItem(item);
        ratings.setRatingId(1);
        ratings.setScore(3);
        ratings.setUser(user);
        Optional<Ratings> ofResult = Optional.of(ratings);
        doNothing().when(ratingsRepository).deleteById(Mockito.<Integer>any());
        when(ratingsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Ratings actualDeleteRatingResult = ratingServicePrimaryImpl.deleteRating(1);

        // Assert
        verify(ratingsRepository).deleteById(eq(1));
        verify(ratingsRepository).findById(eq(1));
        assertSame(ratings, actualDeleteRatingResult);
    }

    /**
     * Method under test: {@link RatingServicePrimaryImpl#deleteRating(Integer)}
     */
    @Test
    void testDeleteRating2() throws EntityNotFoundException {
        // Arrange
        Optional<Ratings> emptyResult = Optional.empty();
        when(ratingsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> ratingServicePrimaryImpl.deleteRating(1));
        verify(ratingsRepository).findById(eq(1));
    }
}
