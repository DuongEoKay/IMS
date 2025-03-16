package org.vanduong.online_food_ordering_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.model.Order;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.service.OrderService;
import org.vanduong.online_food_ordering_system.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController 
{


    @Autowired
    private OrderService orderService;


    @Autowired
    private UserService userService;





    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getRestaurantOrders(
                                             @RequestHeader("Authorization") String jwt,
                                             @PathVariable Long restaurantId,
                                             @PathVariable(required = false) String orderStatus) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Order> order = orderService.getRestaurantOrders(restaurantId, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }


    @PutMapping("/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId,
                                                  @PathVariable String orderStatus,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Order order = orderService.updateOrder(orderId, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }


}
