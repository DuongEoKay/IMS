package com.vanduong.ims.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String adminId;
    
    private String fullName;
    
    private String gender;
    
    private String email;
    
    private String phone;



}
