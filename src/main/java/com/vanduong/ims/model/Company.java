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
public class Company {
    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    
    private String companyId;

    private String companyName;
    
    private String email;
    
    private String phone;
    
    private String address;
    
    private float rating;

}
