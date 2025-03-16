package com.vanduong.ims.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private USER_ROLE role=USER_ROLE.ROLE_STUDENT;

//    @ToString.Exclude
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
//    private List<Order> orders =new ArrayList<>();
//
//    @ElementCollection
//    private List<RestaurantDto>favourites=new ArrayList();
//
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Address> addresses=new ArrayList<>();


}
