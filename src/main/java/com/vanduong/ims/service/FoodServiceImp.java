package org.vanduong.online_food_ordering_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vanduong.online_food_ordering_system.model.Category;
import org.vanduong.online_food_ordering_system.model.Food;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.repository.FoodRepository;
import org.vanduong.online_food_ordering_system.repository.RestaurantRepository;
import org.vanduong.online_food_ordering_system.request.CreateFoodRequest;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService {


    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(createFoodRequest.getDescription());
        food.setVeg(createFoodRequest.getIsVegan());
        food.setImgs(createFoodRequest.getImgs());
        food.setPrice(createFoodRequest.getPrice());
        food.setIngredients(createFoodRequest.getIngredients());
        food.setSeasonal(createFoodRequest.getIsSeasonal());
        food.setName(createFoodRequest.getName());
        food.setCreatedDate(new Date());


        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        System.out.println(createFoodRequest);
        return savedFood;

    }

    @Override
    public Food updateFood(Long foodId, CreateFoodRequest updatedFoodRequest) throws Exception {
        Optional<Food> food = foodRepository.findById(foodId);
        food.get().setName(updatedFoodRequest.getName());
        food.get().setDescription(updatedFoodRequest.getDescription());
        food.get().setPrice(updatedFoodRequest.getPrice());
        food.get().setVeg(updatedFoodRequest.getIsVegan());
        food.get().setSeasonal(updatedFoodRequest.getIsSeasonal());
        food.get().setImgs(updatedFoodRequest.getImgs());
        food.get().setIngredients(updatedFoodRequest.getIngredients());
        food.get().setFoodCategory(updatedFoodRequest.getCategory());
        food.get().setRestaurant(restaurantRepository.findById(updatedFoodRequest.getRestaurantId()).orElseThrow(() -> new Exception("Restaurant not found")));


        return foodRepository.save(food.get());
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new Exception("Food not found"));
        food.setRestaurant(null);
        foodRepository.delete(food);

    }




//        @Override
//    public List<Food> getRestaurantFoods(Long restaurantId, boolean isVegan, boolean isSeasonal, Long categoryId) {
//            List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
//
//if (Boolean.TRUE.equals(isVegan)) {
//    foods.removeIf(food -> !food.isVeg());
//}
//
//if (Boolean.TRUE.equals(isSeasonal)) {
//    foods.removeIf(food -> !food.isSeasonal());
//}
//
//            if (categoryId != null) {
//                foods.removeIf(food -> {
//                    if (food.getFoodCategory() != null) {
//                        return !Objects.equals(food.getFoodCategory().getId(), categoryId);
//                    }
//                    return false;
//                });
//            }
//
//            return foods;
//    }




    @Override
    public List<Food> getRestaurantFoods(Long restaurantId, boolean isVegan, boolean isSeasonal, Long categoryId, boolean all) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if(all==true){
            return foods;
        }



        if(isVegan==true){
            if(isSeasonal==true)
            {


                return foods;
            }
            foods.removeIf(food -> !food.isVeg());
        }
        if(isVegan==false){

            if (isSeasonal) {
                foods.removeIf(food -> !food.isSeasonal());
            }
            else {
                foods.removeIf(food -> food.isVeg());
            }

        }


        return foods;
    }


    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> food = foodRepository.findById(foodId);
        if (food.isEmpty()) {
            throw new Exception("Food not found");
        }
        return food.get();
    }


    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = new Food();
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }

    @Override
    public List<Food> filterByVegetarian(List<Food> foods, boolean isVegan) {
        return foods.stream().filter(food -> food.isVeg()).collect(Collectors.toList());
    }

    @Override
    public List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()).collect(Collectors.toList());
    }

    @Override
    public List<Food> filterByCategory(List<Food> foods, Long categoryId) {
        return foods.stream().filter(food -> {
            if (food.getFoodCategory() != null) {
                return food.getFoodCategory().getName().equals(categoryId);
            }
            return false;
        }).collect(Collectors.toList());
    }
}
