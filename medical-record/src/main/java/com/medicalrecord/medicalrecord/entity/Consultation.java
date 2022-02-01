package com.medicalrecord.medicalrecord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String diagnosis;
    private double weight;
    @Lob
    private String treatment;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date dateRecorded;
    private String blood_pressure;
    @OneToOne(mappedBy = "consultation")
    private Prescription prescription;
    @ManyToOne
    @JoinColumn()
    @NotNull
    private MedicalRecord medicalRecord;
}
