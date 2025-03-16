package org.vanduong.online_food_ordering_system.service;


import org.vanduong.online_food_ordering_system.model.Category;
import org.vanduong.online_food_ordering_system.model.Food;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.request.CreateFoodRequest;

import java.util.List;


public interface FoodService {
    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant) ;

    public Food updateFood(Long foodId,CreateFoodRequest updatedFoodRequest)  throws Exception;

    public void deleteFood(Long foodId) throws Exception;


    public List<Food>getRestaurantFoods(Long restaurantId,
                                        boolean isVegan,
                                        boolean isSeasonal,
                                        Long categoryId, boolean all) ;


    public List<Food> searchFood(String keyword) ;

    public Food findFoodById(Long foodId) throws Exception;


    public Food updateAvailabilityStatus(Long foodId) throws Exception;


    public List<Food> filterByVegetarian(List<Food> foods,  boolean isVegan) ;
    public List<Food> filterBySeasonal(List<Food> foods,boolean isSeasonal) ;
    public List<Food> filterByCategory(List<Food> foods,Long categoryId) ;



}
