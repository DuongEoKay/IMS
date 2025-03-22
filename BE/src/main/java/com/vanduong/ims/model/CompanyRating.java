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
public class CompanyRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    private int rateStar;

    @ManyToOne
    @JoinColumn(name = "student_topic_id")
    private StudentTopic studentTopicId;
    
    
    
}
