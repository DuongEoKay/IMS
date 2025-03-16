package org.vanduong.online_food_ordering_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vanduong.online_food_ordering_system.model.IngredientsCategory;
import org.vanduong.online_food_ordering_system.model.IngredientsItem;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.repository.IngredientCategoryRepository;
import org.vanduong.online_food_ordering_system.repository.IngredientItemRepository;

import java.util.List;
import java.util.Optional;


@Service
public class IngredientsServiceImp implements IngredientsService{


    @Autowired
    IngredientItemRepository ingredientItemRepository;


    @Autowired
    IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    RestaurantService restaurantService;


    @Override
    public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientsCategory category = new IngredientsCategory();

        category.setName(name);

        category.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientsCategory findIngredientsCategoryById(Long categoryId) throws Exception {
        Optional<IngredientsCategory> category = ingredientCategoryRepository.findById(categoryId);

        if(category.isEmpty())
        {
            throw new Exception("Category not found");
        }

        return category.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long restaurantId) throws Exception {

        List<IngredientsCategory> categories = ingredientCategoryRepository.findIngredientsCategoryByRestaurantId(restaurantId);

        if(categories.isEmpty())
        {
            throw new Exception("Category not found");
        }

        return categories;
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientNam, Long ingredientCategoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory category = findIngredientsCategoryById(ingredientCategoryId);
        IngredientsItem ingredient = new IngredientsItem();
        ingredient.setName(ingredientNam);
        ingredient.setRestaurant(restaurant);
        ingredient.setCategory(category);


        IngredientsItem ingredientsItem= ingredientItemRepository.save(ingredient);
        category.getIngredientsItems().add(ingredient);
        return ingredientsItem;

    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        List<IngredientsItem> ingredients = ingredientItemRepository.findByRestaurantId(restaurantId);

        if(ingredients.isEmpty())
        {
            throw new Exception("Ingredients not found");
        }

        return ingredients;
    }

    @Override
    public IngredientsItem updateStock(Long ingredientId) throws Exception {
        Optional<IngredientsItem> ingredient = ingredientItemRepository.findById(ingredientId);
        if (ingredient.isEmpty())
        {
            throw new Exception("Ingredient not found");
        }
        ingredient.get().setAvailable(!ingredient.get().isAvailable());
        return ingredient.get();
    }
}
