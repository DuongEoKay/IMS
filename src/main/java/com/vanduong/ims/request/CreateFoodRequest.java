package org.vanduong.online_food_ordering_system.request;


import lombok.Data;
import org.vanduong.online_food_ordering_system.model.Category;
import org.vanduong.online_food_ordering_system.model.IngredientsItem;

import java.util.List;

@Data
public class CreateFoodRequest {


    private String name;
    private String description;
    private Long price;
    private Category category;

    private List<String> imgs;

    private Long restaurantId;
    private Boolean isVegan;

    private Boolean isSeasonal;

    private List<IngredientsItem> ingredients;

}
