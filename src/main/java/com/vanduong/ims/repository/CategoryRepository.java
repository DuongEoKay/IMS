package org.vanduong.online_food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vanduong.online_food_ordering_system.model.Category;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Long>{

    public List<Category> findCategoriesByRestaurantId(Long restaurantId);

}
