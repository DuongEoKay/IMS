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
public class Teacher {
    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private UUID majorId;

    private String teacherId;
    
    private String fullName;
    
    private String falcuty;
    
    private String phone;
    
    private String teacherType;

}
