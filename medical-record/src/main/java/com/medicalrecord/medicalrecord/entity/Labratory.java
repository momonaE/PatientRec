package com.medicalrecord.medicalrecord.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Labratory {
    @Id
    @GeneratedValue
    private Long id;
    private  String name;
    private Date dateReleased;
    private String resultStatus;
//    @ManyToOne
//    @JoinColumn
//    private MedicalRecord medicalRecord;
}
