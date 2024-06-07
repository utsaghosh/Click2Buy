package com.ecart.ecart.controller;

import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.dto.ItemModel;
import com.ecart.ecart.service.interfaces.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ItemController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ItemControllerDiffblueTest {
    @Autowired
    private ItemController itemController;

    @MockBean
    private ItemService itemService;

    /**
     * Method under test: {@link ItemController#getAllItems()}
     */
    @Test
    void testGetAllItems() throws Exception {
        // Arrange
        when(itemService.getAllItems()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/getallitems");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ItemController#getAllItems()}
     */
    @Test
    void testGetAllItems2() throws Exception {
        // Arrange
        Items items = new Items();
        items.setCartEntriesList(new ArrayList<>());
        items.setItemDescription("Requesting all items from database");
        items.setItemId(1);
        items.setItemImage("Requesting all items from database");
        items.setItemName("Requesting all items from database");
        items.setItemPrice(42);
        items.setRatingsList(new ArrayList<>());
        items.setReviewsList(new ArrayList<>());

        ArrayList<Items> itemsList = new ArrayList<>();
        itemsList.add(items);
        when(itemService.getAllItems()).thenReturn(itemsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/getallitems");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"itemId\":1,\"itemPrice\":42,\"itemName\":\"Requesting all items from database\",\"itemDescription\":\"Requesting"
                                        + " all items from database\",\"itemImage\":\"Requesting all items from database\",\"averageRating\":0.0,"
                                        + "\"modelValid\":true}]"));
    }

    /**
     * Method under test: {@link ItemController#getItemById(Integer)}
     */
    @Test
    void testGetItemById() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/getbyid/{itemid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"itemId\":1,\"itemPrice\":42,\"itemName\":\"Item Name\",\"itemDescription\":\"Item Description\",\"itemImage\":\"Item"
                                        + " Image\",\"averageRating\":0.0,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link ItemController#getItemsMatchingName(String)}
     */
    @Test
    void testGetItemsMatchingName() throws Exception {
        // Arrange
        when(itemService.getItemsMatchingName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/getmatchitem/{searchtext}",
                "Searchtext");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ItemController#getItemsMatchingName(String)}
     */
    @Test
    void testGetItemsMatchingName2() throws Exception {
        // Arrange
        Items items = new Items();
        items.setCartEntriesList(new ArrayList<>());
        items.setItemDescription("Requesting all items from database matching name {}");
        items.setItemId(1);
        items.setItemImage("Requesting all items from database matching name {}");
        items.setItemName("Requesting all items from database matching name {}");
        items.setItemPrice(42);
        items.setRatingsList(new ArrayList<>());
        items.setReviewsList(new ArrayList<>());

        ArrayList<Items> itemsList = new ArrayList<>();
        itemsList.add(items);
        when(itemService.getItemsMatchingName(Mockito.<String>any())).thenReturn(itemsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/getmatchitem/{searchtext}",
                "Searchtext");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"itemId\":1,\"itemPrice\":42,\"itemName\":\"Requesting all items from database matching name {}\","
                                        + "\"itemDescription\":\"Requesting all items from database matching name {}\",\"itemImage\":\"Requesting all"
                                        + " items from database matching name {}\",\"averageRating\":0.0,\"modelValid\":true}]"));
    }

    /**
     * Method under test: {@link ItemController#addItem(ItemModel)}
     */
    @Test
    void testAddItem() throws Exception {
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
        when(itemService.addItem(Mockito.<ItemModel>any())).thenReturn(items);

        ItemModel itemModel = new ItemModel();
        itemModel.setAverageRating(10.0f);
        itemModel.setItemDescription("Item Description");
        itemModel.setItemId(1);
        itemModel.setItemImage("Item Image");
        itemModel.setItemName("Item Name");
        itemModel.setItemPrice(42);
        String content = (new ObjectMapper()).writeValueAsString(itemModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/items/additem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"itemId\":1,\"itemPrice\":42,\"itemName\":\"Item Name\",\"itemDescription\":\"Item Description\",\"itemImage\":\"Item"
                                        + " Image\",\"averageRating\":0.0,\"modelValid\":true}"));
    }

    /**
     * Method under test: {@link ItemController#deleteItem(Integer)}
     */
    @Test
    void testDeleteItem() throws Exception {
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
        when(itemService.deleteItem(Mockito.<Integer>any())).thenReturn(items);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/items/delete/{itemid}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"itemId\":1,\"itemPrice\":42,\"itemName\":\"Item Name\",\"itemDescription\":\"Item Description\",\"itemImage\":\"Item"
                                        + " Image\",\"averageRating\":0.0,\"modelValid\":true}"));
    }
}
