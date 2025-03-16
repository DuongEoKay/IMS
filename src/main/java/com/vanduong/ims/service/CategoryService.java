package org.vanduong.online_food_ordering_system.service;

import org.vanduong.online_food_ordering_system.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> findCategoriesByRestaurantId(Long restaurantId)  throws Exception;

    public Category findCategoryById(Long categoryId) throws Exception;

    public Category updateCategory(Long categoryId, String name) throws Exception;

}
