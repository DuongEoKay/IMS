package org.vanduong.online_food_ordering_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vanduong.online_food_ordering_system.model.*;
import org.vanduong.online_food_ordering_system.repository.AddressRepository;
import org.vanduong.online_food_ordering_system.repository.OrderItemRepository;
import org.vanduong.online_food_ordering_system.repository.OrderRepository;
import org.vanduong.online_food_ordering_system.repository.UserRepository;
import org.vanduong.online_food_ordering_system.request.OrderRequest;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;


    @Override
    public Order createOrder(OrderRequest orderRequest, User user) throws Exception {
        Address shippingAddress = orderRequest.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);


        if(!user.getAddresses().contains(savedAddress))
        {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(orderRequest.getRestaurantId());


        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setRestaurant(restaurant);
        createdOrder.setCreatedDate(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);


        Cart cart = cartService.findCartByUserId(user.getId());


        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem: cart.getCartItems())
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);

        }

        Long totalPrice= cartService.calculateCartTotal(cart);

        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(createdOrder);
        restaurant.getOrders().add(savedOrder);

        return createdOrder;





    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId).get();
        if(orderStatus.equals("CANCELLED")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING"))
        {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        else
        {
            throw new Exception("Invalid Order Status");
        }
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId).get();
        orderRepository.delete(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders= orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null)
        {
            orders=orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).toList();
        }
        return orders;
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty())
        {
            throw new Exception("Order not found");
        }
        return order;
    }
}
