package com.ecart.ecart.controller;

import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Reviews;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.dto.ReviewModel;
import com.ecart.ecart.service.interfaces.ReviewService;
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

@ContextConfiguration(classes = {ReviewController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReviewControllerDiffblueTest {
    @Autowired
    private ReviewController reviewController;

    @MockBean
    private ReviewService reviewService;

    /**
     * Method under test: {@link ReviewController#getReviewById(Integer)}
     */
    @Test
    void testGetReviewById() throws Exception {
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
        when(reviewService.getReviewById(Mockito.<Integer>any())).thenReturn(reviews);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/getbyid/{reviewid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"reviewId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"body\":\"Not all who wander are lost\","
                                        + "\"creationDate\":[1970,1,1,0,0],\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link ReviewController#getAllReviewsOfItem(Integer)}
     */
    @Test
    void testGetAllReviewsOfItem() throws Exception {
        // Arrange
        when(reviewService.getAllReviewsOfItem(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/getitemreviews/{itemid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReviewController#getAllReviewsOfItem(Integer)}
     */
    @Test
    void testGetAllReviewsOfItem2() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Requesting Reviews of Item with id = {}");
        item.setItemId(1);
        item.setItemImage("Requesting Reviews of Item with id = {}");
        item.setItemName("Requesting Reviews of Item with id = {}");
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

        ArrayList<Reviews> reviewsList = new ArrayList<>();
        reviewsList.add(reviews);
        when(reviewService.getAllReviewsOfItem(Mockito.<Integer>any())).thenReturn(reviewsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/getitemreviews/{itemid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"reviewId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"body\":\"Not all who wander are lost\","
                                        + "\"creationDate\":[1970,1,1,0,0],\"modelValid\":true}]"));
    }

    /**
     * Method under test: {@link ReviewController#getAllReviewsByUser(Integer)}
     */
    @Test
    void testGetAllReviewsByUser() throws Exception {
        // Arrange
        when(reviewService.getAllReviewsByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/getreviewsbyuser/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReviewController#getAllReviewsByUser(Integer)}
     */
    @Test
    void testGetAllReviewsByUser2() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Requesting Reviews by User with id = {}");
        item.setItemId(1);
        item.setItemImage("Requesting Reviews by User with id = {}");
        item.setItemName("Requesting Reviews by User with id = {}");
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

        ArrayList<Reviews> reviewsList = new ArrayList<>();
        reviewsList.add(reviews);
        when(reviewService.getAllReviewsByUser(Mockito.<Integer>any())).thenReturn(reviewsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/getreviewsbyuser/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"reviewId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"body\":\"Not all who wander are lost\","
                                        + "\"creationDate\":[1970,1,1,0,0],\"modelValid\":true}]"));
    }

    /**
     * Method under test:
     * {@link ReviewController#getReviewOfItemByUser(Integer, Integer)}
     */
    @Test
    void testGetReviewOfItemByUser() throws Exception {
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
        when(reviewService.getReviewOfItemByUser(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(reviews);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/getreview/{itemid}/{userid}", 1,
                1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"reviewId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"body\":\"Not all who wander are lost\","
                                        + "\"creationDate\":[1970,1,1,0,0],\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link ReviewController#addReview(ReviewModel)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddReview() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.ecart.ecart.model.ReviewModel["creationDate"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setBody("Not all who wander are lost");
        reviewModel.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        reviewModel.setItemId(1);
        reviewModel.setReviewId(1);
        reviewModel.setUserId(1);
        reviewModel.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(reviewModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reviews/addreview")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(reviewController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ReviewController#deleteReview(Integer)}
     */
    @Test
    void testDeleteReview() throws Exception {
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
        when(reviewService.deleteReview(Mockito.<Integer>any())).thenReturn(reviews);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/reviews/delete/{reviewid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"reviewId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"body\":\"Not all who wander are lost\","
                                        + "\"creationDate\":[1970,1,1,0,0],\"modelValid\":true}"));
    }

    /**
     * Method under test:
     * {@link ReviewController#updateReview(Integer, ReviewModel)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateReview() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.ecart.ecart.model.ReviewModel["creationDate"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setBody("Not all who wander are lost");
        reviewModel.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        reviewModel.setItemId(1);
        reviewModel.setReviewId(1);
        reviewModel.setUserId(1);
        reviewModel.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(reviewModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/reviews/updatereview/{reviewid}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(reviewController).build().perform(requestBuilder);
    }
}
