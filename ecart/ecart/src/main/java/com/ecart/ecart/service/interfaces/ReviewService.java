package com.ecart.ecart.service.interfaces;

import com.ecart.ecart.entity.Reviews;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.ReviewModel;

import java.util.List;

public interface ReviewService {

    Reviews addReview(ReviewModel reviewInput) throws EntityNotFoundException, InvalidInputException, EntityAlreadyExistsException;

    //read
    Reviews getReviewById(Integer reviewId) throws EntityNotFoundException;

    List<Reviews> getAllReviewsOfItem(Integer itemId) throws EntityNotFoundException;

    List<Reviews> getAllReviewsByUser(Integer userId) throws EntityNotFoundException;

    Reviews getReviewOfItemByUser(Integer itemId, Integer userId) throws EntityNotFoundException;

    //update
    Reviews updateReview(Integer reviewId, ReviewModel reviewInput) throws EntityNotFoundException, InvalidInputException;


    //delete methods
    Reviews deleteReview(Integer reviewId) throws EntityNotFoundException;
}
