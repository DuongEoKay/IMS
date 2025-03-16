package org.vanduong.online_food_ordering_system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//@Document(indexName = "restaurant")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User owner;

    private String name;

    private String description;

    private String cuisineType;

    @OneToOne
    private Address address;


    @Embedded
    private ContactInformation contact;

    private String openingHours;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders=new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> imgs;

    private LocalDateTime registeredAt;

    private boolean isOpen;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> foods=new ArrayList<>();



    public void setContactInformation(ContactInformation contactInformation) {
        this.contact = contactInformation;
    }






}
