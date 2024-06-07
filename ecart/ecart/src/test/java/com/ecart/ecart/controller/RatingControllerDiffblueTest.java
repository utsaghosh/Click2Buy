package com.ecart.ecart.controller;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Ratings;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.dto.Gender;
import com.ecart.ecart.dto.RatingModel;
import com.ecart.ecart.service.interfaces.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RatingController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RatingControllerDiffblueTest {
    @Autowired
    private RatingController ratingController;

    @MockBean
    private RatingService ratingService;

    /**
     * Method under test: {@link RatingController#getRatingById(Integer)}
     */
    @Test
    void testGetRatingById() throws Exception {
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
        when(ratingService.getRatingById(Mockito.<Integer>any())).thenReturn(ratings);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ratings/getbyid/{ratingid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"ratingId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"score\":3,\"creationDate\":[1970,1,1,0,0]}"));
    }

    /**
     * Method under test: {@link RatingController#getAllRatingsOfItem(Integer)}
     */
    @Test
    void testGetAllRatingsOfItem() throws Exception {
        // Arrange
        when(ratingService.getAllRatingsOfItem(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ratings/getitemratings/{itemid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RatingController#getAllRatingsOfItem(Integer)}
     */
    @Test
    void testGetAllRatingsOfItem2() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Requesting Ratings of Item with id = {}");
        item.setItemId(1);
        item.setItemImage("Requesting Ratings of Item with id = {}");
        item.setItemName("Requesting Ratings of Item with id = {}");
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

        ArrayList<Ratings> ratingsList = new ArrayList<>();
        ratingsList.add(ratings);
        when(ratingService.getAllRatingsOfItem(Mockito.<Integer>any())).thenReturn(ratingsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ratings/getitemratings/{itemid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"ratingId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"score\":3,\"creationDate\":[1970,1,1,0,0]}]"));
    }

    /**
     * Method under test: {@link RatingController#getAllRatingsByUser(Integer)}
     */
    @Test
    void testGetAllRatingsByUser() throws Exception {
        // Arrange
        when(ratingService.getAllRatingsByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ratings/getratingsbyuser/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RatingController#getAllRatingsByUser(Integer)}
     */
    @Test
    void testGetAllRatingsByUser2() throws Exception {
        // Arrange
        Items item = new Items();
        item.setCartEntriesList(new ArrayList<>());
        item.setItemDescription("Requesting Ratings by User with id = {}");
        item.setItemId(1);
        item.setItemImage("Requesting Ratings by User with id = {}");
        item.setItemName("Requesting Ratings by User with id = {}");
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

        ArrayList<Ratings> ratingsList = new ArrayList<>();
        ratingsList.add(ratings);
        when(ratingService.getAllRatingsByUser(Mockito.<Integer>any())).thenReturn(ratingsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ratings/getratingsbyuser/{userid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"ratingId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"score\":3,\"creationDate\":[1970,1,1,0,0]}]"));
    }

    /**
     * Method under test:
     * {@link RatingController#getRatingOfItemByUser(Integer, Integer)}
     */
    @Test
    void testGetRatingOfItemByUser() throws Exception {
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
        when(ratingService.getRatingOfItemByUser(Mockito.<Integer>any(), Mockito.<Integer>any())).thenReturn(ratings);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ratings/getrating/{itemid}/{userid}", 1,
                1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"ratingId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"score\":3,\"creationDate\":[1970,1,1,0,0]}"));
    }

    /**
     * Method under test: {@link RatingController#addRating(RatingModel)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddRating() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.ecart.ecart.model.RatingModel["creationDate"])
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
        RatingModel ratingModel = new RatingModel();
        ratingModel.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratingModel.setItemId(1);
        ratingModel.setRatingId(1);
        ratingModel.setScore(3);
        ratingModel.setUserId(1);
        ratingModel.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(ratingModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ratings/addrating")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(ratingController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"ratingId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"score\":3,\"creationDate\":[1970,1,1,0]}"));
    }

    /**
     * Method under test: {@link RatingController#deleteRating(Integer)}
     */
    @Test
    void testDeleteRating() throws Exception {
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
        when(ratingService.deleteRating(Mockito.<Integer>any())).thenReturn(ratings);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ratings/delete/{ratingid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"ratingId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"score\":3,\"creationDate\":[1970,1,1,0,0]}"));
    }

    /**
     * Method under test:
     * {@link RatingController#updateRating(Integer, RatingModel)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateRating() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.ecart.ecart.model.RatingModel["creationDate"])
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
        RatingModel ratingModel = new RatingModel();
        ratingModel.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        ratingModel.setItemId(1);
        ratingModel.setRatingId(1);
        ratingModel.setScore(3);
        ratingModel.setUserId(1);
        ratingModel.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(ratingModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/ratings/updaterating/{ratingid}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(ratingController).build().perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"ratingId\":1,\"itemId\":1,\"userId\":1,\"userName\":\"janedoe\",\"score\":3,\"creationDate\":[1970,1,1,0]}"));;
    }
}
