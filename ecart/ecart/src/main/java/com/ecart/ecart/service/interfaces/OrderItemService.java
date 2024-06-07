package com.ecart.ecart.service.interfaces;

import com.ecart.ecart.entity.OrderItems;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;

import java.util.List;

public interface OrderItemService {

    List<OrderItems> placeOrder(Integer userId) throws EntityNotFoundException;

    OrderItems cancelOrder(Integer orderItemId) throws EntityNotFoundException, InvalidInputException;

    List<OrderItems> getOrdersByUser(Integer userId) throws EntityNotFoundException;

    Boolean checkItemOrderedByUser(Integer itemId, Integer userId) throws EntityNotFoundException;
}
