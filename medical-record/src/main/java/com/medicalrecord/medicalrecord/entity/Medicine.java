package com.medicalrecord.medicalrecord.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int iQuantity = 0;
    @NotNull
    private String name;
    private String purpose;
    @Temporal(TemporalType.DATE)
    private Date dateDelivered;
}
