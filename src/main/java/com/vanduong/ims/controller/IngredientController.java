package org.vanduong.online_food_ordering_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.model.IngredientsCategory;
import org.vanduong.online_food_ordering_system.model.IngredientsItem;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.request.IngredientCategoryRequest;
import org.vanduong.online_food_ordering_system.request.IngredientRequest;
import org.vanduong.online_food_ordering_system.service.IngredientsService;

import java.util.List;

@RestController
@RequestMapping("/admin/ingredient")
public class IngredientController {


    @Autowired
    private IngredientsService ingredientService;


    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest ingredientsCategoryRequest,
                                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        IngredientsCategory ingredientsCategory = ingredientService.createIngredientsCategory(ingredientsCategoryRequest.getName(), ingredientsCategoryRequest.getRestaurantId());


        return ResponseEntity.ok(ingredientsCategory);

    }

    @PostMapping("/item")
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest ingredientRequest,
                                                                @RequestHeader("Authorization") String jwt) throws Exception {
        IngredientsItem ingredientsItem = ingredientService.createIngredientsItem(ingredientRequest.getRestaurantId(), ingredientRequest.getName(), ingredientRequest.getCategoryId());
        return ResponseEntity.ok(ingredientsItem);

    }


    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(@RequestParam Long id,
                                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        IngredientsItem ingredientsItem = ingredientService.updateStock(id);
        return ResponseEntity.ok(ingredientsItem);

    }


    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(@PathVariable Long restaurantId,
                                                                @RequestHeader("Authorization") String jwt) throws Exception {
        List<IngredientsItem> ingredientsItem = ingredientService.findRestaurantIngredients(restaurantId);
        return ResponseEntity.ok(ingredientsItem);

    }


    @GetMapping("/restaurant/{restaurantId}/category")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientCategory(@PathVariable Long restaurantId,
                                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        List<IngredientsCategory> ingredientsCategories = ingredientService.findIngredientsCategoryByRestaurantId(restaurantId);
        return ResponseEntity.ok(ingredientsCategories);

    }












}
