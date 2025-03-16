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
public class StudentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String studentTopicId;
    
    private long studentId;
    
    private long topicId;
    
    private boolean isApproved;
    
    private boolean isScored;
    
    private boolean isCompleted;
    
    private long assignedTeacherId;
    
    private float score;
    
    private boolean isScheduled;
    
    private long scheduledId;
    
    

}
