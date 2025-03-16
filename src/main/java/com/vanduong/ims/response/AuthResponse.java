package org.vanduong.online_food_ordering_system.response;

import lombok.Data;
import org.vanduong.online_food_ordering_system.model.USER_ROLE;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private USER_ROLE role;
}
