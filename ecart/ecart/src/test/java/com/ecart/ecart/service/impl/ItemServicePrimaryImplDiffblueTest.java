package com.ecart.ecart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.ItemModel;
import com.ecart.ecart.repository.ItemsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ItemServicePrimaryImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ItemServicePrimaryImplDiffblueTest {
    @Autowired
    private ItemServicePrimaryImpl itemServicePrimaryImpl;

    @MockBean
    private ItemsRepository itemsRepository;

    /**
     * Method under test: {@link ItemServicePrimaryImpl#addItem(ItemModel)}
     */
    @Test
    void testAddItem() throws InvalidInputException {
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
        when(itemsRepository.save(Mockito.<Items>any())).thenReturn(items);

        ItemModel itemModel = new ItemModel();
        itemModel.setItemPrice(42);

        // Act
        Items actualAddItemResult = itemServicePrimaryImpl.addItem(itemModel);

        // Assert
        verify(itemsRepository).save(isA(Items.class));
        assertSame(items, actualAddItemResult);
    }

    /**
     * Method under test: {@link ItemServicePrimaryImpl#addItem(ItemModel)}
     */
    @Test
    void testAddItem2() throws InvalidInputException {
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
        when(itemsRepository.save(Mockito.<Items>any())).thenReturn(items);

        Items items2 = new Items();
        items2.setCartEntriesList(new ArrayList<>());
        items2.setItemDescription("Item Description");
        items2.setItemId(1);
        items2.setItemImage("Item Image");
        items2.setItemName("Item Name");
        items2.setItemPrice(42);
        items2.setRatingsList(new ArrayList<>());
        items2.setReviewsList(new ArrayList<>());
        ItemModel itemModel = mock(ItemModel.class);
        when(itemModel.parseEntityFromModel()).thenReturn(items2);
        doNothing().when(itemModel).setItemPrice(Mockito.<Integer>any());
        itemModel.setItemPrice(42);

        // Act
        Items actualAddItemResult = itemServicePrimaryImpl.addItem(itemModel);

        // Assert
        verify(itemModel).parseEntityFromModel();
        verify(itemModel).setItemPrice(eq(42));
        verify(itemsRepository).save(isA(Items.class));
        assertSame(items, actualAddItemResult);
    }

    /**
     * Method under test: {@link ItemServicePrimaryImpl#getAllItems()}
     */
    @Test
    void testGetAllItems() throws EntityNotFoundException {
        // Arrange
        when(itemsRepository.findAll(Mockito.<Sort>any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> itemServicePrimaryImpl.getAllItems());
        verify(itemsRepository).findAll(isA(Sort.class));
    }

    /**
     * Method under test: {@link ItemServicePrimaryImpl#getAllItems()}
     */
    @Test
    void testGetAllItems2() throws EntityNotFoundException {
        // Arrange
        Items items = new Items();
        items.setCartEntriesList(new ArrayList<>());
        items.setItemDescription("Fetching all items from database");
        items.setItemId(1);
        items.setItemImage("Fetching all items from database");
        items.setItemName("Fetching all items from database");
        items.setItemPrice(42);
        items.setRatingsList(new ArrayList<>());
        items.setReviewsList(new ArrayList<>());

        ArrayList<Items> itemsList = new ArrayList<>();
        itemsList.add(items);
        when(itemsRepository.findAll(Mockito.<Sort>any())).thenReturn(itemsList);

        // Act
        List<Items> actualAllItems = itemServicePrimaryImpl.getAllItems();

        // Assert
        verify(itemsRepository).findAll(isA(Sort.class));
        assertEquals(1, actualAllItems.size());
        assertSame(itemsList, actualAllItems);
    }

    /**
     * Method under test:
     * {@link ItemServicePrimaryImpl#getItemsMatchingName(String)}
     */
    @Test
    void testGetItemsMatchingName() throws EntityNotFoundException {
        // Arrange
        ArrayList<Items> itemsList = new ArrayList<>();
        when(itemsRepository.findByItemNameLike(Mockito.<String>any())).thenReturn(itemsList);

        // Act
        List<Items> actualItemsMatchingName = itemServicePrimaryImpl.getItemsMatchingName("Name Search");

        // Assert
        verify(itemsRepository).findByItemNameLike(eq("Name Search"));
        assertTrue(actualItemsMatchingName.isEmpty());
        assertSame(itemsList, actualItemsMatchingName);
    }

    /**
     * Method under test: {@link ItemServicePrimaryImpl#getItemById(Integer)}
     */
    @Test
    void testGetItemById() throws EntityNotFoundException {
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
        Optional<Items> ofResult = Optional.of(items);
        when(itemsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Items actualItemById = itemServicePrimaryImpl.getItemById(1);

        // Assert
        verify(itemsRepository).findById(eq(1));
        assertSame(items, actualItemById);
    }

    /**
     * Method under test: {@link ItemServicePrimaryImpl#getItemById(Integer)}
     */
    @Test
    void testGetItemById2() throws EntityNotFoundException {
        // Arrange
        Optional<Items> emptyResult = Optional.empty();
        when(itemsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> itemServicePrimaryImpl.getItemById(1));
        verify(itemsRepository).findById(eq(1));
    }

    /**
     * Method under test: {@link ItemServicePrimaryImpl#deleteItem(Integer)}
     */
    @Test
    void testDeleteItem() throws EntityNotFoundException {
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
        Optional<Items> ofResult = Optional.of(items);
        doNothing().when(itemsRepository).deleteById(Mockito.<Integer>any());
        when(itemsRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Items actualDeleteItemResult = itemServicePrimaryImpl.deleteItem(1);

        // Assert
        verify(itemsRepository).deleteById(eq(1));
        verify(itemsRepository).findById(eq(1));
        assertSame(items, actualDeleteItemResult);
    }

    /**
     * Method under test: {@link ItemServicePrimaryImpl#deleteItem(Integer)}
     */
    @Test
    void testDeleteItem2() throws EntityNotFoundException {
        // Arrange
        Optional<Items> emptyResult = Optional.empty();
        when(itemsRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> itemServicePrimaryImpl.deleteItem(1));
        verify(itemsRepository).findById(eq(1));
    }
}
