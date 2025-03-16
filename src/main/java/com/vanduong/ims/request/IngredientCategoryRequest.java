package org.vanduong.online_food_ordering_system.request;


import lombok.Data;

@Data
public class IngredientCategoryRequest {
    private String name;
    private Long restaurantId;
}
