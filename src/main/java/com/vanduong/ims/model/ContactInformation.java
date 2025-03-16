package org.vanduong.online_food_ordering_system.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ContactInformation {

    private String email;
    private String phone;
    private String facebook;
    private String instagram;
}
