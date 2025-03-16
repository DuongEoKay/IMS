package org.vanduong.online_food_ordering_system.service;

import org.vanduong.online_food_ordering_system.dto.RestaurantDto;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest createRestaurantRequest, User user) ;


    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updatedRestaurantRequest)  throws Exception;


    public void deleteRestaurant(Long restaurantId) throws Exception; ;

    public List<Restaurant> getAllRestaurants() ;

    public List<Restaurant> searchRestaurant(String keyword) ;


    public Restaurant findRestaurantById(Long restaurantId) throws Exception;


    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorite(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id);
}
