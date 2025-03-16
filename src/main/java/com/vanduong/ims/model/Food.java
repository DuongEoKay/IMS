package org.vanduong.online_food_ordering_system.model;


import jakarta.persistence.*;
import lombok.*;
import org.apache.el.parser.BooleanNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    private String name;


    private String description;

    private Long price;

    @ManyToOne
    private Category foodCategory;


    @Column(length = 1000)
    @ElementCollection
    private List<String> imgs;

    private boolean isAvailable;


    @ManyToOne
    private Restaurant restaurant;

    private boolean isVeg;


    private boolean isSeasonal;

    @ManyToMany
    private List<IngredientsItem> ingredients=new ArrayList<>();

    private Date createdDate;


}
