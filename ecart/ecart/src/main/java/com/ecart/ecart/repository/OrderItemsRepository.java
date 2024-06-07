package com.ecart.ecart.repository;

import com.ecart.ecart.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    @Query(value = "select o from OrderItems o where o.user.userId = ?1 order by o.creationDate desc")
    List<OrderItems> findByUserId(Integer userId);

    @Query(value = "select o from OrderItems o where o.item.itemId=?1 and o.user.userId=?2")
    List<OrderItems> findByItemIdAndUserId(Integer itemId, Integer userId);
}
