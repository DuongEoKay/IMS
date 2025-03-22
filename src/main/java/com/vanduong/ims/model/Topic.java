package com.vanduong.ims.model;


import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.*;
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
    private UUID id;

    
    private String topicName;
    
    private String jobDescription;
    
    private int quntity;
    
    private String duration;
    
    private Date endRegisterDate;
    
    private Date startDate;
    
    private Date endDate;
    
    private String approvalStatus;


    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major majorId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companyId;
   
    
}
