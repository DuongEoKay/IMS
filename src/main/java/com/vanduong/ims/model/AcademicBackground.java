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
public class AcademicBackground {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String academicBackgroundId;
    
    private long StudentId;
    
    private long SubjectId;
    
    private float gpa;


}
