package org.vanduong.online_food_ordering_system.request;

import lombok.Data;
import org.vanduong.online_food_ordering_system.model.Address;
import org.vanduong.online_food_ordering_system.model.ContactInformation;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private Address address;
    private String cuisineStyle;
    private ContactInformation contact;
    private String openingHours;
    private List<String> imgs;
    private LocalDateTime registerAt;

}
