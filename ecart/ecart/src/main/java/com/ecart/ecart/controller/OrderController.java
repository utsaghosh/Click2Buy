package com.ecart.ecart.controller;

import com.ecart.ecart.exception.EntityNotFoundException;
import com.ecart.ecart.exception.InvalidInputException;
import com.ecart.ecart.dto.OrderItemModel;
import com.ecart.ecart.service.interfaces.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    OrderItemService orderService;

    @PostMapping("/place/{userid}")
    ResponseEntity<List<OrderItemModel>> placeOrder(@PathVariable("userid") Integer userId) throws EntityNotFoundException {
        log.info("Request to place order for cart of user={}", userId);
        List<OrderItemModel> orders = OrderItemModel.parseModelListFromEntityList(orderService.placeOrder(userId));
        log.info("Response: {}", orders);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/cancel/{orderid}")
    ResponseEntity<OrderItemModel> cancelorder(@PathVariable("orderid") Integer orderId) throws EntityNotFoundException, InvalidInputException {
        log.info("Request to cancel order(id= {})", orderId);
        OrderItemModel cancelled = new OrderItemModel( orderService.cancelOrder(orderId) );
        log.info("Cancelled order: {}", cancelled);
        return new ResponseEntity<>(cancelled, HttpStatus.OK);
    }

    @GetMapping("/check/{itemid}/{userid}")
    public ResponseEntity<Boolean> checkItemOrderedByUser(@PathVariable("itemid") Integer itemId, @PathVariable("userid") Integer userId) throws EntityNotFoundException {
        log.info("Request check if item={} ordered by user={}", itemId, userId);
        Boolean check = orderService.checkItemOrderedByUser(itemId, userId);
        log.info("Response: {}", check);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

    @GetMapping("/getbyuser/{userid}")
    ResponseEntity<List<OrderItemModel>> getOrdersByUser(@PathVariable("userid") Integer userId) throws EntityNotFoundException {
        log.info("Request to get all orders by user={}", userId);
        List<OrderItemModel> orders = OrderItemModel.parseModelListFromEntityList(orderService.getOrdersByUser(userId));
        log.info("Response: {}", orders);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
