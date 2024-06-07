package com.ecart.ecart.service.interfaces;

import com.ecart.ecart.entity.Ratings;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.RatingModel;

import java.util.List;

public interface RatingService {

    //add
    Ratings addRating(RatingModel ratingInput) throws EntityNotFoundException, InvalidInputException, EntityAlreadyExistsException;

    //read
    Ratings getRatingById(Integer ratingId) throws EntityNotFoundException;

    List<Ratings> getAllRatingsOfItem(Integer itemId) throws EntityNotFoundException;

    List<Ratings> getAllRatingsByUser(Integer userId) throws EntityNotFoundException;

    Ratings getRatingOfItemByUser(Integer itemId, Integer userId) throws EntityNotFoundException;

    //update
    Ratings updateRating(Integer ratingId, RatingModel ratingInput) throws EntityNotFoundException, InvalidInputException;


    //delete methods
    Ratings deleteRating(Integer ratingId) throws EntityNotFoundException;
}
