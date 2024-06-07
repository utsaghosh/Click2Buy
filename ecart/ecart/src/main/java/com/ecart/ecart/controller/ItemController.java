package com.ecart.ecart.controller;

import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.ItemModel;
import com.ecart.ecart.service.interfaces.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/items")
@Slf4j
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/getallitems")
    public ResponseEntity<List<ItemModel>> getAllItems() throws EntityNotFoundException {

        log.info("Requesting all items from database");

        List<ItemModel> items = ItemModel.parseModelListFromEntityList(itemService.getAllItems());

        log.info("Response: {}", items);

        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @GetMapping("/getbyid/{itemid}")
    public ResponseEntity<ItemModel> getItemById(@PathVariable("itemid") Integer itemId) throws EntityNotFoundException {

        log.info("Requesting all items from database");

        ItemModel item = new ItemModel(itemService.getItemById(itemId));

        log.info("Response: {}", item);

        return new ResponseEntity<>(item, HttpStatus.OK);

    }

    @GetMapping("/getmatchitem/{searchtext}")
    public ResponseEntity<List<ItemModel>> getItemsMatchingName(@PathVariable("searchtext") String nameSearch) throws EntityNotFoundException {

        log.info("Requesting all items from database matching name {}", nameSearch);

        List<ItemModel> items = ItemModel.parseModelListFromEntityList(itemService.getItemsMatchingName(nameSearch));

        log.info("Response: {}", items);

        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @PostMapping("/additem")
    public ResponseEntity<ItemModel> addItem(@RequestBody ItemModel itemModel) throws InvalidInputException {
        log.info("Requested adding new item with data {}", itemModel);
        ItemModel added = new ItemModel(itemService.addItem(itemModel));
        log.info("New item added with data {}", added);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{itemid}")
    public ResponseEntity<ItemModel> deleteItem(@PathVariable("itemid") Integer itemId) throws EntityNotFoundException {
        log.info("Requested deletion of item woth id = {}", itemId);
        ItemModel item = new ItemModel(itemService.deleteItem(itemId));
        log.info("Response: {}", item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
