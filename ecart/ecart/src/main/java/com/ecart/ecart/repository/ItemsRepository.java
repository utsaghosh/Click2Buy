package com.ecart.ecart.repository;

import com.ecart.ecart.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {
    @Query(value = "select i from Items i where i.itemName like concat('%',?1,'%') order by i.itemId desc")
    List<Items> findByItemNameLike(String nameSearch);
}
