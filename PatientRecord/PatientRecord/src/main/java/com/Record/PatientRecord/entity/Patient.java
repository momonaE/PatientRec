package com.Record.PatientRecord.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    @Id
    @GeneratedValue
    private  Long patientId;
    private String firstName;
    private String lastName;
    private  String gender;
    private int age;
    private Date dateOfBirth;
    @Embedded
    private Address address;

}
