package com.medicalrecord.medicalrecord.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long patientId;
    @NotNull
    private  Long doctorId;
//    @NotNull
//    private int recordNo;
    @OneToMany(mappedBy = "medicalRecord")
    private List<Consultation> consultations  = new ArrayList<>();
//    @OneToMany(mappedBy = "medicalRecord")
//    private List<Labratory> labRecords = new ArrayList<>();
//    @OneToMany(mappedBy = "medicalRecord")
//    private List<Bill> bills = new ArrayList<>();


}
