package org.vanduong.online_food_ordering_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.model.Food;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.repository.FoodRepository;
import org.vanduong.online_food_ordering_system.request.CreateFoodRequest;
import org.vanduong.online_food_ordering_system.response.MessageResponse;
import org.vanduong.online_food_ordering_system.service.FoodService;
import org.vanduong.online_food_ordering_system.service.RestaurantService;
import org.vanduong.online_food_ordering_system.service.UserService;

@RestController
@RequestMapping("/admin/food")
public class AdminFoodController {


    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;



    @PostMapping("/")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest createFoodRequest,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user= userService.findUserByJwt(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(createFoodRequest.getRestaurantId());
        Food food = foodService.createFood(createFoodRequest, createFoodRequest.getCategory(), restaurant);

        return ResponseEntity.ok(food);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@RequestHeader("Authorization") String jwt,
                                           @PathVariable Long id) throws Exception {

        User user= userService.findUserByJwt(jwt);

        Food food = foodService.findFoodById(id);

        foodService.deleteFood(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Food deleted successfully");


        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateFoodAvaibilityStatus(@RequestHeader("Authorization") String jwt,
                                                      @RequestParam Long id) throws Exception {

        User user= userService.findUserByJwt(jwt);

        Food food = foodService.updateAvailabilityStatus(id);

        MessageResponse  messageResponse = new MessageResponse();
        messageResponse.setMessage("Food updated successfully");

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }


}
