package com.ecart.ecart.service.impl;

import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Reviews;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.ReviewModel;
import com.ecart.ecart.repository.ReviewsRepository;
import com.ecart.ecart.service.interfaces.ItemService;
import com.ecart.ecart.service.interfaces.ReviewService;
import com.ecart.ecart.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Qualifier("reviewService")
@Slf4j
public class ReviewServicePrimaryImpl implements ReviewService {

    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    ReviewsRepository reviewsRepository;
    
    @Override
    public Reviews addReview(ReviewModel reviewInput) throws EntityNotFoundException, InvalidInputException, EntityAlreadyExistsException {

        log.info("Adding new review with: {}", reviewInput);

        if(! reviewInput.isModelValid()) {
            return null;
        }

        Users user = userService.getUserById(reviewInput.getUserId());
        log.info("User Found");

        Items item = itemService.getItemById(reviewInput.getItemId());
        log.info("Item Found");

        Reviews existingReview = reviewsRepository.findByItemIdAndUserId(item.getItemId(), user.getUserId());

        if(existingReview != null) {
            log.info("New review not added");
            throw new EntityAlreadyExistsException("User has already rated the item, may update review");
        }

        String body = reviewInput.getBody();

        Reviews review = new Reviews();
        review.setUser(user);
        review.setItem(item);
        review.setBody(body);
        review.setCreationDate(LocalDateTime.now());
        review = reviewsRepository.save(review);

        log.info("New review added successfully with id = {}", review.getReviewId());
        return review;
    }

    @Override
    public Reviews getReviewById(Integer reviewId) throws EntityNotFoundException {

        log.info("Fetching review with id = {}", reviewId);
        Reviews review = reviewsRepository.findById(reviewId).orElse(null);
        if(review == null) {
            throw new EntityNotFoundException("Review Not Found");
        }
        log.info("Review fetched successfully");
        return review;
    }

    @Override
    public List<Reviews> getAllReviewsOfItem(Integer itemId) throws EntityNotFoundException {

        log.info("Fetching all reviews of item with id = {}", itemId);

        Items item = itemService.getItemById(itemId);
        log.info("Item Found");

        List<Reviews> reviewsList = reviewsRepository.findByItemId(item.getItemId());

//        if(reviewsList.isEmpty()) {
//            throw new EntityNotFoundException("No review found for item "+item.getItemName());
//        }
        log.info("Fetched all reviews of item with id = {}, {} reviews found", itemId, reviewsList.size());
        return reviewsList;
    }

    @Override
    public List<Reviews> getAllReviewsByUser(Integer userId) throws EntityNotFoundException {

        log.info("Fetching all reviews by user with id = {}", userId);

        Users user = userService.getUserById(userId);
        log.info("User Found");

        List<Reviews> reviewsList = reviewsRepository.findByUserId(user.getUserId());

//        if(reviewsList.isEmpty()) {
//            throw new EntityNotFoundException("No reviews found by user "+user.getUserName());
//        }
        log.info("Fetched all reviews by user with id = {}, {} reviews found", userId, reviewsList.size());
        return reviewsList;
    }

    @Override
    public Reviews getReviewOfItemByUser(Integer itemId, Integer userId) throws EntityNotFoundException {

        log.info("Fetching review of item with id= {}, by user with id= {}", itemId, userId);

        Items item = itemService.getItemById(itemId);
        log.info("Item Found");

        Users user = userService.getUserById(userId);
        log.info("User Found");

        Reviews review = reviewsRepository.findByItemIdAndUserId(item.getItemId(), user.getUserId());

        if(review == null) {
            throw new EntityNotFoundException("User"+user.getUserName()+" has not reviewed item "+item.getItemName());
        }

        log.info("Review of {} by {} is {} fetched successfully", item.getItemName(), user.getUserName(), review.getBody());
        return review;
    }

    @Override
    public Reviews updateReview(Integer reviewId, ReviewModel reviewInput) throws EntityNotFoundException, InvalidInputException {

        log.info("Updating review with id={} with {}", reviewId, reviewInput);

        Reviews review = this.getReviewById(reviewId);

        if (! review.getUser().getUserId().equals(reviewInput.getUserId())) {
            log.info("Review not updated");
            throw new EntityNotFoundException("User mismatch");
        }

        if(!Objects.equals(review.getItem().getItemId(), reviewInput.getItemId())) {
            log.info("Review not updated");
            throw new EntityNotFoundException("Item mismatch");
        }

        if(! reviewInput.isModelValid()) {
            return null;
        }

        review.setBody(reviewInput.getBody());
        review.setCreationDate(LocalDateTime.now());

        review = reviewsRepository.save(review);

        log.info("Review updated successfully");
        return review;
    }

    @Override
    public Reviews deleteReview(Integer reviewId) throws EntityNotFoundException {

        log.info("Deleting review with id={}", reviewId);

        Reviews review = this.getReviewById(reviewId);

        reviewsRepository.deleteById(reviewId);

        log.info("Review deleted successfully");

        return review;
    }
}
