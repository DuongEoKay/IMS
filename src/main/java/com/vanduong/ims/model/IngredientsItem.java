package org.vanduong.online_food_ordering_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IngredientsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    @ManyToOne
    private IngredientsCategory category;


    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;


    private boolean isAvailable=true;



}
