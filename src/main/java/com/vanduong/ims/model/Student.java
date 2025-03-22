package com.vanduong.ims.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student{
    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "major_id")
    private UUID majorId;

    private String studenId;


    private String fullName;
    
    private String gender;
    
    private String falculty;
    
    private float gpa;
    
    private String className;
    
    private String email;
    
    private String phone;
    
    private String totalCredit;
    
    private String academicYear;
    
    private Boolean isQualified;



}
