package com.vanduong.ims.model;


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
public class Student extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private long majorId;

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
