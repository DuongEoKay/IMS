package org.vanduong.online_food_ordering_system.service;

import org.vanduong.online_food_ordering_system.model.IngredientsCategory;
import org.vanduong.online_food_ordering_system.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception;

    public IngredientsCategory findIngredientsCategoryById(Long categoryId) throws Exception;

    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long restaurantId) throws Exception;


    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientNam, Long ingredientCategoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception;

    public IngredientsItem updateStock(Long ingredientId) throws Exception;


}
