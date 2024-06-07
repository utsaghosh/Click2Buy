package com.ecart.ecart.service.impl;

import com.ecart.ecart.entity.CartItems;
import com.ecart.ecart.entity.Items;
import com.ecart.ecart.entity.OrderItems;
import com.ecart.ecart.entity.Users;
import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.repository.OrderItemsRepository;
import com.ecart.ecart.service.interfaces.CartItemService;
import com.ecart.ecart.service.interfaces.ItemService;
import com.ecart.ecart.service.interfaces.OrderItemService;
import com.ecart.ecart.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Qualifier("orderService")
@Slf4j
public class OrderItemServicePrimaryImpl implements OrderItemService {

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    CartItemService cartService;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Override
    public List<OrderItems> placeOrder(Integer userId) throws EntityNotFoundException {
        List<OrderItems> orders = new ArrayList<>();
        List<CartItems> cartItemList = cartService.getUserCart(userId);
        if(cartItemList.isEmpty()){
            throw new EntityNotFoundException("Cannot place order User's cart is empty");
        }
        for(CartItems cartItem : cartItemList){

            OrderItems orderItem = new OrderItems();
            orderItem.setItem(cartItem.getItem());
            orderItem.setOrderPrice(cartItem.getItemPrice() * cartItem.getItemCount());
            orderItem.setItemCount(cartItem.getItemCount());
            orderItem.setUser(cartItem.getUser());
            orderItem.setAddress(userService.getUserById(userId).getAddress());
            orderItem.setCreationDate(LocalDateTime.now());

            orderItem = orderItemsRepository.save(orderItem);
            log.info("New orderItem created {}", orderItem.getOrderPrice());


            orders.add(orderItem);
        }
        for(CartItems cartItem : cartItemList){
            cartService.deleteCartItem(cartItem.getCartItemId());
        }
        return orders;
    }

    @Override
    public OrderItems cancelOrder(Integer orderItemId) throws EntityNotFoundException, InvalidInputException {

        OrderItems order = orderItemsRepository.findById(orderItemId).orElse(null);
        if (order == null){
            throw new EntityNotFoundException("Order cannot be found");
        }
        else{
            long minutesElapsed = ChronoUnit.MINUTES.between(order.getCreationDate(), LocalDateTime.now());
            if (minutesElapsed > 5){
                throw new InvalidInputException("This order cannot be cancelled");
            }
            else {
                orderItemsRepository.deleteById(order.getOrderItemId());
                return order;
            }
        }
    }

    @Override
    public List<OrderItems> getOrdersByUser(Integer userId) throws EntityNotFoundException {
        Users user = userService.getUserById(userId);
        //        if(orders.isEmpty()){
//            throw new EntityNotFoundException("User has not placed any orders yet");
//        }
        return orderItemsRepository.findByUserId(user.getUserId());
    }

    @Override
    public Boolean checkItemOrderedByUser(Integer itemId, Integer userId) throws EntityNotFoundException {
        Items item = itemService.getItemById(itemId);
        log.info("Item Found");
        Users user = userService.getUserById(userId);
        log.info("User Found");

        List<OrderItems> orderItems = orderItemsRepository.findByItemIdAndUserId(item.getItemId(), user.getUserId());
        return !orderItems.isEmpty();
    }
}
