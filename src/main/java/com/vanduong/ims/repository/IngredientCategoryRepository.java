package org.vanduong.online_food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vanduong.online_food_ordering_system.model.IngredientsCategory;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientsCategory, Long>{
    List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long restaurantId);
}
