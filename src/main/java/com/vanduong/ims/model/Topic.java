package com.vanduong.ims.model;


import java.sql.Date;

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
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String topicId;
    
    private String topicName;
    
    private String jobDescription;
    
    private int quntity;
    
    private String duration;
    
    private Date endRegisterDate;
    
    private Date startDate;
    
    private Date endDate;
    
    private String approvalStatus;
    
    private long majorId;
    
    private long companyId;
   
    
}
