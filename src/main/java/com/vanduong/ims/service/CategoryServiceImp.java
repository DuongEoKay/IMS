package org.vanduong.online_food_ordering_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vanduong.online_food_ordering_system.model.Category;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp  implements  CategoryService{




    @Autowired
    private RestaurantService restaurantService;


    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);

        
    }

    @Override
    public List<Category> findCategoriesByRestaurantId(Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        List<Category> category = categoryRepository.findCategoriesByRestaurantId(restaurant.getId());
        if(category.isEmpty())
        {
            throw new Exception("Category not found");
        }
        return category;

    }

    @Override
    public Category findCategoryById(Long categoryId) throws Exception {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty())
        {
            throw new Exception("Category not found");
        }
        return category.get();
    }

    @Override
    public Category updateCategory(Long categoryId, String name) throws Exception {
        return null;
    }
}
