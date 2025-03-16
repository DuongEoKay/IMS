package org.vanduong.online_food_ordering_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.dto.RestaurantDto;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.request.CreateRestaurantRequest;
import org.vanduong.online_food_ordering_system.service.RestaurantService;
import org.vanduong.online_food_ordering_system.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant( @RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception {

        User user = userService.findUserByJwt(jwt);

        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }



    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getAllRestaurant( @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById( @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Restaurant restaurants = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @PutMapping("/{id}/add-favourite")
    public ResponseEntity<RestaurantDto> addToFavourites( @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwt(jwt);

        RestaurantDto restaurants = restaurantService.addToFavorite(id, user);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }





}
