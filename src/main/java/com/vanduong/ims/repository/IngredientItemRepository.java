package org.vanduong.online_food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vanduong.online_food_ordering_system.model.IngredientsCategory;
import org.vanduong.online_food_ordering_system.model.IngredientsItem;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {

    List<IngredientsItem>findIngredientsItemByRestaurantId(Long restaurantId);


    List<IngredientsItem>findByRestaurantId(Long restaurantId);
}
