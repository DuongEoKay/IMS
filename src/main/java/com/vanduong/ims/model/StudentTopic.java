package com.vanduong.ims.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topicId;
    
    private boolean isApproved;
    
    private boolean isScored;
    
    private boolean isCompleted;


    @ManyToOne
    @JoinColumn(name = "assigned_teacher_id")
    private Teacher assignedTeacherId;
    
    private float score;
    
    private boolean isScheduled;


    @ManyToOne
    @JoinColumn(name = "scheduled_id")
    private InterviewSchedule scheduledId;
    
    

}
