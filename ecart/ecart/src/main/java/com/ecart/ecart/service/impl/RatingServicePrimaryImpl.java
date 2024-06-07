package com.ecart.ecart.service.impl;

import com.ecart.ecart.repository.RatingsRepository;
import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.Ratings;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityAlreadyExistsException;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.RatingModel;
import com.ecart.ecart.service.interfaces.ItemService;
import com.ecart.ecart.service.interfaces.RatingService;
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
@Qualifier("ratingService")
@Slf4j
public class RatingServicePrimaryImpl implements RatingService {

    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    RatingsRepository ratingsRepository;

    @Override
    public Ratings addRating(RatingModel ratingInput) throws EntityNotFoundException, InvalidInputException, EntityAlreadyExistsException {

        log.info("Adding new rating with: {}", ratingInput);

        Users user = userService.getUserById(ratingInput.getUserId());
        log.info("User Found");

        Items item = itemService.getItemById(ratingInput.getItemId());
        log.info("Item Found");

        Ratings existingRating = ratingsRepository.findByItemIdAndUserId(item.getItemId(), user.getUserId());

        if(existingRating != null) {
            log.info("New rating not added");
            throw new EntityAlreadyExistsException("User has already rated the item, may update rating");
        }

        Integer score = ratingInput.getScore();

        if(score == null || score < 1 || score > 5) {
            log.info("New rating not added");
            throw new InvalidInputException("Score must be between 1 and 5 (both inclusive)");
        }

        Ratings rating = new Ratings();
        rating.setUser(user);
        rating.setItem(item);
        rating.setScore(score);
        rating.setCreationDate(LocalDateTime.now());
        rating = ratingsRepository.save(rating);

        log.info("New rating added successfully with id = {}", rating.getRatingId());
        return rating;
    }

    @Override
    public Ratings getRatingById(Integer ratingId) throws EntityNotFoundException {

        log.info("Fetching rating with id = {}", ratingId);
        Ratings rating = ratingsRepository.findById(ratingId).orElse(null);
        if(rating == null) {
            throw new EntityNotFoundException("Rating Not Found");
        }
        log.info("Rating fetched successfully");
        return rating;
    }

    @Override
    public List<Ratings> getAllRatingsOfItem(Integer itemId) throws EntityNotFoundException {
        
        log.info("Fetching all ratings of item with id = {}", itemId);

        Items item = itemService.getItemById(itemId);
        log.info("Item Found");

        List<Ratings> ratingsList = ratingsRepository.findByItemId(item.getItemId());

//        if(ratingsList.isEmpty()) {
//            throw new EntityNotFoundException("No rating found for item "+item.getItemName());
//        }
        log.info("Fetched all ratings of item with id = {}, {} ratings found", itemId, ratingsList.size());
        return ratingsList;
    }

    @Override
    public List<Ratings> getAllRatingsByUser(Integer userId) throws EntityNotFoundException {

        log.info("Fetching all ratings by user with id = {}", userId);

        Users user = userService.getUserById(userId);
        log.info("User Found");

        List<Ratings> ratingsList = ratingsRepository.findByUserId(user.getUserId());

//        if(ratingsList.isEmpty()) {
//            throw new EntityNotFoundException("No ratings found by user "+user.getUserName());
//        }
        log.info("Fetched all ratings by user with id = {}, {} ratings found", userId, ratingsList.size());
        return ratingsList;
    }

    @Override
    public Ratings getRatingOfItemByUser(Integer itemId, Integer userId) throws EntityNotFoundException {

        log.info("Fetching rating of item with id= {}, by user with id= {}", itemId, userId);

        Items item = itemService.getItemById(itemId);
        log.info("Item Found");

        Users user = userService.getUserById(userId);
        log.info("User Found");

        Ratings rating = ratingsRepository.findByItemIdAndUserId(item.getItemId(), user.getUserId());

        if(rating == null) {
            throw new EntityNotFoundException("User"+user.getUserName()+" has not reviewed item "+item.getItemName());
        }

        log.info("Rating of {} by {} is {} fetched successfully", item.getItemName(), user.getUserName(), rating.getScore());
        return rating;
    }

    @Override
    public Ratings updateRating(Integer ratingId, RatingModel ratingInput) throws EntityNotFoundException, InvalidInputException {

        log.info("Updating rating with id={} with {}", ratingId, ratingInput);

        Ratings rating = this.getRatingById(ratingId);

        if (! rating.getUser().getUserId().equals(ratingInput.getUserId())) {
            log.info("Rating not updated");
            throw new EntityNotFoundException("User mismatch");
        }

        if(!Objects.equals(rating.getItem().getItemId(), ratingInput.getItemId())) {
            log.info("Rating not updated");
            throw new EntityNotFoundException("Item mismatch");
        }

        if(ratingInput.getScore() == null || ratingInput.getScore() < 1 || ratingInput.getScore() > 5) {
            log.info("Rating not updated");
            throw new InvalidInputException("Score must be between 1 and 5 (both inclusive)");
        }

        rating.setScore(ratingInput.getScore());
        rating.setCreationDate(LocalDateTime.now());

        rating = ratingsRepository.save(rating);

        log.info("Rating updated successfully");
        return rating;
    }

    @Override
    public Ratings deleteRating(Integer ratingId) throws EntityNotFoundException {

        log.info("Deleting rating with id={}", ratingId);

        Ratings rating = this.getRatingById(ratingId);

        ratingsRepository.deleteById(ratingId);

        log.info("Rating deleted successfully");

        return rating;
    }
}
