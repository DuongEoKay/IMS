package org.vanduong.online_food_ordering_system.service;

import org.vanduong.online_food_ordering_system.model.Order;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.request.OrderRequest;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public Order createOrder(OrderRequest orderRequest, User user) throws Exception;

    public Order updateOrder(Long orderId, String OrderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOrders(Long userId) throws Exception;

    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception;


    public Optional<Order> findOrderById(Long orderId) throws Exception;


}
