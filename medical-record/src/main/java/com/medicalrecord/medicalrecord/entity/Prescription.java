package com.medicalrecord.medicalrecord.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private String amount;
    @Lob
    private String notes;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Medicine> medicines = new ArrayList<>();
    @OneToOne
    @NotNull
    private Consultation consultation;
}
