package com.ecart.ecart.dto;

import com.ecart.ecart.entity.Ratings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingModel {

    private Integer ratingId;
    private Integer itemId;
    private Integer userId;
    private String userName;
    private Integer score;
    private LocalDateTime creationDate;

    public RatingModel (Ratings rating){

        this.setRatingId(rating.getRatingId());
        this.setItemId(rating.getItem().getItemId());
        this.setUserId(rating.getUser().getUserId());
        this.setScore(rating.getScore());
        this.setUserName(rating.getUser().getUserName());
        this.setCreationDate(rating.getCreationDate());

    }

    public static List<RatingModel> parseModelListFromEntityList(List<Ratings> ratings){

        List<RatingModel> ratingModelList = new ArrayList<RatingModel>();

        for (Ratings ratingEntity : ratings){
            ratingModelList.add(new RatingModel(ratingEntity));
        }

        return  ratingModelList;
    }
}
