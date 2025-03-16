package org.vanduong.online_food_ordering_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.model.Category;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.request.CreateRestaurantRequest;
import org.vanduong.online_food_ordering_system.response.MessageResponse;
import org.vanduong.online_food_ordering_system.service.CategoryService;
import org.vanduong.online_food_ordering_system.service.RestaurantService;
import org.vanduong.online_food_ordering_system.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;




    @PostMapping("/admin/category")
    public ResponseEntity<MessageResponse> createCategory(@RequestBody Category category,
                                                          @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        
        Category newCategory = categoryService.createCategory(category.getName(), user.getId());

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Category created successfully");

        return ResponseEntity.ok(messageResponse);
    }



    @GetMapping("/category/{restaurantId}")
    public ResponseEntity<List<Category>> findCategoryByRestaurantId(
                                                          @RequestHeader("Authorization") String jwt, @PathVariable Long restaurantId) throws Exception {

        User user = userService.findUserByJwt(jwt);


        List<Category> newCategory = categoryService.findCategoriesByRestaurantId(restaurantId);



        return ResponseEntity.ok(newCategory);
    }
}
