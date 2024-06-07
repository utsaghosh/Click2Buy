package com.ecart.ecart.dto;

import com.ecart.ecart.entity.Reviews;
import com.ecart.ecart.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModel {

    private Integer reviewId;
    private Integer itemId;
    private Integer userId;
    private String userName;
    private String body;
    private LocalDateTime creationDate;

    public ReviewModel (Reviews review){

        this.setReviewId(review.getReviewId());
        this.setItemId(review.getItem().getItemId());
        this.setUserId(review.getUser().getUserId());
        this.setBody(review.getBody());
        this.setUserName(review.getUser().getUserName());
        this.setCreationDate(review.getCreationDate());

    }

    public static List<ReviewModel> parseModelListFromEntityList(List<Reviews> reviews){

        List<ReviewModel> reviewModelList = new ArrayList<ReviewModel>();

        for (Reviews reviewEntity : reviews){
            reviewModelList.add(new ReviewModel(reviewEntity));
        }

        return  reviewModelList;
    }

    public boolean isModelValid() throws InvalidInputException {

        if( this.getBody() == null) {
            throw new InvalidInputException("Review body absent");
        }
        if( this.getBody().isBlank() ) {
            throw new InvalidInputException("Review body cannot be whitespace");
        }
        if( this.getBody().length() > 16777215 ) {
            throw new InvalidInputException("Review body cannot exceed max character limit of 16777215");
        }

        return true;
    }
}
