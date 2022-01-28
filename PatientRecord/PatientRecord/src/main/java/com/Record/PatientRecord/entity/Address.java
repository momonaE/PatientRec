package com.Record.PatientRecord.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String state ;
    private  String zipCode;
    private  String city;
    private String streetAddress;

}
