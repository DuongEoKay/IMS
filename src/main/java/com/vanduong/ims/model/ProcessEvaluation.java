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
public class ProcessEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String processEvaluationId;
    
    private String processEvaluationName;
    
    private String comment;

    @ManyToOne
    @JoinColumn(name = "student_topic_id")
    private StudentTopic studentTopicId;
    
    

}
