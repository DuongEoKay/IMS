package org.vanduong.online_food_ordering_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.model.Food;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.request.CreateFoodRequest;
import org.vanduong.online_food_ordering_system.service.FoodService;
import org.vanduong.online_food_ordering_system.service.RestaurantService;
import org.vanduong.online_food_ordering_system.service.UserService;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;



    @GetMapping("/")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserByJwt(jwt);

        List<Food> food = foodService.searchFood(keyword);

        return ResponseEntity.ok(food);
    }


    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFoods(@PathVariable Long restaurantId,
                                                        @RequestParam(required = false) boolean isVegan,
                                                        @RequestParam(required = false) boolean isSeasonal,
                                                        @RequestParam (required = false)Long categoryId,
                                                         @RequestParam (required = false)boolean all,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {



        User user = userService.findUserByJwt(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        List<Food> food = foodService.getRestaurantFoods(restaurantId,isVegan,isSeasonal,categoryId, all);



        return ResponseEntity.ok(food);
    }




}
