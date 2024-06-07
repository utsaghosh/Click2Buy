package com.ecart.ecart.controller;

import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.ReviewModel;
import com.ecart.ecart.service.interfaces.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/addreview")
    public ResponseEntity<ReviewModel> addReview(@RequestBody ReviewModel reviewModel) throws InvalidInputException, EntityAlreadyExistsException, EntityNotFoundException {

        log.info("Requesting adding new review with data {}", reviewModel);

        ReviewModel added = new ReviewModel(reviewService.addReview(reviewModel));

        log.info("New review added with data {}", added);

        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{reviewid}")
    public  ResponseEntity<ReviewModel> getReviewById(@PathVariable("reviewid") Integer reviewId) throws EntityNotFoundException {

        log.info("Requesting Review with id = {}", reviewId);

        ReviewModel review = new ReviewModel(reviewService.getReviewById(reviewId));

        log.info("Response : {}", review);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/getitemreviews/{itemid}")
    public  ResponseEntity<List<ReviewModel>> getAllReviewsOfItem(@PathVariable("itemid") Integer itemId) throws EntityNotFoundException {

        log.info("Requesting Reviews of Item with id = {}", itemId);

        List<ReviewModel> reviews = ReviewModel.parseModelListFromEntityList(reviewService.getAllReviewsOfItem(itemId));

        log.info("Response : {}", reviews);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/getreviewsbyuser/{userid}")
    public  ResponseEntity<List<ReviewModel>> getAllReviewsByUser(@PathVariable("userid") Integer userId) throws EntityNotFoundException {

        log.info("Requesting Reviews by User with id = {}", userId);

        List<ReviewModel> reviews = ReviewModel.parseModelListFromEntityList(reviewService.getAllReviewsByUser(userId));

        log.info("Response : {}", reviews);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/getreview/{itemid}/{userid}")
    public  ResponseEntity<ReviewModel> getReviewOfItemByUser(@PathVariable("itemid") Integer itemId, @PathVariable("userid") Integer userId) throws EntityNotFoundException {

        log.info("Requesting Review of item = {} by user = {}", itemId, userId);

        ReviewModel review = new ReviewModel(reviewService.getReviewOfItemByUser(itemId, userId));

        log.info("Response : {}", review);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("updatereview/{reviewid}")
    public  ResponseEntity<ReviewModel> updateReview(@PathVariable("reviewid") Integer reviewId, @RequestBody ReviewModel reviewInput) throws InvalidInputException, EntityNotFoundException {

        log.info("Requesting update of review with id={} with new data : {}", reviewId, reviewInput);

        ReviewModel updated = new ReviewModel(reviewService.updateReview(reviewId,reviewInput));

        log.info("Review updated to : {}", updated);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("delete/{reviewid}")
    public ResponseEntity<ReviewModel> deleteReview(@PathVariable("reviewid") Integer reviewId) throws EntityNotFoundException {

        log.info("Request deletion of review with id={}", reviewId);

        ReviewModel deleted = new ReviewModel(reviewService.deleteReview(reviewId));

        log.info("Review deleted : {}", deleted);

        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
