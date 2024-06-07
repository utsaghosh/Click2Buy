package com.ecart.ecart.controller;

import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.RatingModel;
import com.ecart.ecart.service.interfaces.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ratings")
@Slf4j
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/addrating")
    public ResponseEntity<RatingModel> addRating(@RequestBody RatingModel ratingModel) throws InvalidInputException, EntityAlreadyExistsException, EntityNotFoundException {

        log.info("Requesting adding new rating with data {}", ratingModel);
        RatingModel added = new RatingModel(ratingService.addRating(ratingModel));
        log.info("New rating added with data {}", added);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{ratingid}")
    public  ResponseEntity<RatingModel> getRatingById(@PathVariable("ratingid") Integer ratingId) throws EntityNotFoundException {
        log.info("Requesting Rating with id = {}", ratingId);
        RatingModel rating = new RatingModel(ratingService.getRatingById(ratingId));
        log.info("Response : {}", rating);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("/getitemratings/{itemid}")
    public  ResponseEntity<List<RatingModel>> getAllRatingsOfItem(@PathVariable("itemid") Integer itemId) throws EntityNotFoundException {
        log.info("Requesting Ratings of Item with id = {}", itemId);
        List<RatingModel> ratings = RatingModel.parseModelListFromEntityList(ratingService.getAllRatingsOfItem(itemId));
        log.info("Response : {}", ratings);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/getratingsbyuser/{userid}")
    public  ResponseEntity<List<RatingModel>> getAllRatingsByUser(@PathVariable("userid") Integer userId) throws EntityNotFoundException {
        log.info("Requesting Ratings by User with id = {}", userId);
        List<RatingModel> ratings = RatingModel.parseModelListFromEntityList(ratingService.getAllRatingsByUser(userId));
        log.info("Response : {}", ratings);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/getrating/{itemid}/{userid}")
    public  ResponseEntity<RatingModel> getRatingOfItemByUser(@PathVariable("itemid") Integer itemId, @PathVariable("userid") Integer userId) throws EntityNotFoundException {
        log.info("Requesting Rating of item = {} by user = {}", itemId, userId);
        RatingModel rating = new RatingModel(ratingService.getRatingOfItemByUser(itemId, userId));
        log.info("Response : {}", rating);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PutMapping("updaterating/{ratingid}")
    public  ResponseEntity<RatingModel> updateRating(@PathVariable("ratingid") Integer ratingId, @RequestBody RatingModel ratingInput) throws InvalidInputException, EntityNotFoundException {

        log.info("Requesting update of rating with id={} with new data : {}", ratingId, ratingInput);
        RatingModel updated = new RatingModel(ratingService.updateRating(ratingId,ratingInput));
        log.info("Rating updated to : {}", updated);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("delete/{ratingid}")
    public ResponseEntity<RatingModel> deleteRating(@PathVariable("ratingid") Integer ratingId) throws EntityNotFoundException {
        log.info("Request deletion of rating with id={}", ratingId);
        RatingModel deleted = new RatingModel(ratingService.deleteRating(ratingId));
        log.info("Rating deleted : {}", deleted);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
