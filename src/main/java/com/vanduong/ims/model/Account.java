package com.vanduong.ims.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private USER_ROLE role=USER_ROLE.ROLE_STUDENT;


}
