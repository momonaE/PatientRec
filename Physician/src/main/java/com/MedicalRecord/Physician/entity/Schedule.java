package com.MedicalRecord.Physician.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class Schedule {
    @Id
    @GeneratedValue
    private Long scheduleId;
    private Date availableTime;
    private boolean available = true;
}
