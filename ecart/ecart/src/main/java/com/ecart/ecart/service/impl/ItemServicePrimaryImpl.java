package com.ecart.ecart.service.impl;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.ItemModel;
import com.ecart.ecart.repository.ItemsRepository;
import com.ecart.ecart.service.interfaces.ItemService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Qualifier("itemService")
@Slf4j
public class ItemServicePrimaryImpl implements ItemService {

    @Autowired
    ItemsRepository itemsRepository;

    @Override
    public Items addItem(ItemModel itemModel) throws InvalidInputException {
        
        log.info("Adding new item with: {}", itemModel);

        Items item = itemModel.parseEntityFromModel();
        item = itemsRepository.save(item);

        log.info("New item added successfully with id = {}", item.getItemId());

        return item;
    }

    @Override
    public List<Items> getAllItems() throws EntityNotFoundException {

        log.info("Fetching all items from database");

        List<Items> itemsList = itemsRepository.findAll(Sort.by(Sort.Direction.DESC, "itemId"));
        if(itemsList.isEmpty()) {
            throw new EntityNotFoundException("No Items Found In Database");
        }

        log.info("All items fetched from database: {} items found", +itemsList.size());

        return itemsList;
    }

    @Override
    public List<Items> getItemsMatchingName(String nameSearch) throws EntityNotFoundException {
        
        log.info("Fetching items with name like "+nameSearch);

        List<Items> itemsList = itemsRepository.findByItemNameLike(nameSearch);
//        if(itemsList.isEmpty()) {
//            throw new EntityNotFoundException("No Items Found with name matching "+nameSearch);
//        }

        log.info("Items with name like {} fetched: {} matches found", nameSearch, itemsList.size());

        return itemsList;
    }

    @Override
    public Items getItemById(Integer itemId) throws EntityNotFoundException {

        log.info("Fetching item with id = {}", itemId);

        Items item = itemsRepository.findById(itemId).orElse(null);
        if( item==null ) {
            throw new EntityNotFoundException("Item Not Found");
        }

        log.info("Item with id = {}, name = {} fetched successfully", item.getItemId(), item.getItemName());

        return item;
    }

    @Override
    public Items deleteItem(Integer itemId) throws EntityNotFoundException {
        
        log.info("Deleting item with id = {}", itemId);

        Items item = this.getItemById(itemId);

        itemsRepository.deleteById(itemId);

        log.info("Item with id = {}, name = {} deleted successfully", item.getItemId(), item.getItemName());

        return item;
    }
}
