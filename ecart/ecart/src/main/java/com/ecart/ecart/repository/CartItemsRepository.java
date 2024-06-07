package com.ecart.ecart.repository;

import com.ecart.ecart.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
    @Query(value = "select c from CartItems c where c.item.itemId=?1 and c.user.userId=?2")
    CartItems findByItemIdAndUserId(Integer itemId, Integer userId);

    @Query(value = "select c from CartItems c where c.user.userId=?1 order by c.creationDate desc")
    List<CartItems> findByUserId(Integer userId);
}
