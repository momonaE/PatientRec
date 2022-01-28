package com.MedicalRecord.Physician.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Physician {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long physicianId;
    private String physicianName;
    private String specialize;
    @Embedded
    private Schedule schedule;

}
