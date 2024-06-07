package com.ecart.ecart.dto;

import com.ecart.ecart.entity.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {

    //order specific
    private Integer orderItemId;
    private Integer orderPrice;
    private Integer itemCount;
    private LocalDateTime creationDate;
    //item specific
    private Integer itemId;
    private String itemName;
    private String itemImage;
    //user specific
    private Integer userId;
    private String address;

    public OrderItemModel(OrderItems orderItem){

        this.setOrderItemId(orderItem.getOrderItemId());
        this.setOrderPrice(orderItem.getOrderPrice());
        this.setItemCount(orderItem.getItemCount());
        this.setCreationDate(orderItem.getCreationDate());
        this.setItemId(orderItem.getItem().getItemId());
        this.setItemName(orderItem.getItem().getItemName());
        this.setItemImage(orderItem.getItem().getItemImage());
        this.setUserId(orderItem.getUser().getUserId());
        this.setAddress(orderItem.getUser().getAddress());
    }

    public static List<OrderItemModel> parseModelListFromEntityList(List<OrderItems> orderItemsEntityList){
        List<OrderItemModel> orderItemModelList = new ArrayList<>();
        for(OrderItems orderItemEntity : orderItemsEntityList){
            orderItemModelList.add(new OrderItemModel(orderItemEntity));
        }
        return orderItemModelList;
    }
}
