package org.vanduong.online_food_ordering_system.request;

import lombok.Data;
import org.vanduong.online_food_ordering_system.model.Address;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;

}
