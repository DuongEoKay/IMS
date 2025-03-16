package org.vanduong.online_food_ordering_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.model.CartItem;
import org.vanduong.online_food_ordering_system.model.Order;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.request.OrderRequest;
import org.vanduong.online_food_ordering_system.response.PaymentResponse;
import org.vanduong.online_food_ordering_system.service.OrderService;
import org.vanduong.online_food_ordering_system.service.PaymentService;
import org.vanduong.online_food_ordering_system.service.UserService;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest request,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Order order = orderService.createOrder(request, user);
        
        PaymentResponse res =paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }


    @GetMapping("/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Order> order = orderService.getUserOrders(user.getId());
        return new ResponseEntity(order, HttpStatus.OK);

    }



}
