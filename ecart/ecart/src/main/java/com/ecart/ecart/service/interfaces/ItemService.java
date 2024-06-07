package com.ecart.ecart.service.interfaces;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.dto.ItemModel;

import java.util.List;

public interface ItemService {

    Items addItem(ItemModel itemModel) throws InvalidInputException;

    List<Items> getAllItems() throws EntityNotFoundException;

    List<Items> getItemsMatchingName(String nameSearch) throws EntityNotFoundException;

    Items getItemById(Integer itemId) throws EntityNotFoundException;

    Items deleteItem(Integer itemId) throws EntityNotFoundException;
}
