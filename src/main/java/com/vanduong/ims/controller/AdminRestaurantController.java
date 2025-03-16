package org.vanduong.online_food_ordering_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.request.CreateRestaurantRequest;
import org.vanduong.online_food_ordering_system.response.MessageResponse;
import org.vanduong.online_food_ordering_system.service.RestaurantService;
import org.vanduong.online_food_ordering_system.service.UserService;

@RestController
@RequestMapping("/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Restaurant>createRestaurant(@RequestBody CreateRestaurantRequest rq, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(rq, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Restaurant>updateRestaurant(@RequestBody CreateRestaurantRequest rq, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(id, rq);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse>deleteRestaurant( @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwt(jwt);

        restaurantService.deleteRestaurant(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Restaurant deleted successfully");

        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }



    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant>updateRestaurantStatus(@RequestBody CreateRestaurantRequest rq, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);


        return new ResponseEntity<>(restaurant , HttpStatus.OK);
    }



    @GetMapping("/user")
    public ResponseEntity<Restaurant>findRestaurantByUserId( @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());


        return new ResponseEntity<>(restaurant , HttpStatus.OK);
    }


}
