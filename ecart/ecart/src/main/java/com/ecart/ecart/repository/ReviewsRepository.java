package com.ecart.ecart.repository;

import com.ecart.ecart.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    @Query(value = "select r from Reviews r where r.item.itemId=?1 and r.user.userId=?2")
    Reviews findByItemIdAndUserId(Integer itemId, Integer userId);

    @Query(value = "select r from Reviews r where r.item.itemId=?1 order by r.creationDate desc")
    List<Reviews> findByItemId(Integer itemId);

    @Query(value = "select r from Reviews r where r.user.userId=?1 order by r.creationDate desc")
    List<Reviews> findByUserId(Integer userId);
}
