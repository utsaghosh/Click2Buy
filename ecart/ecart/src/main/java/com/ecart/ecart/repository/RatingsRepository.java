package com.ecart.ecart.repository;

import com.ecart.ecart.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer> {
    @Query(value = "select r from Ratings r where r.item.itemId=?1 and r.user.userId=?2")
    Ratings findByItemIdAndUserId(Integer itemId, Integer userId);

    @Query(value = "select r from Ratings r where r.item.itemId=?1 order by r.creationDate desc")
    List<Ratings> findByItemId(Integer itemId);

    @Query(value = "select r from Ratings r where r.user.userId=?1 order by r.creationDate desc")
    List<Ratings> findByUserId(Integer userId);
}
