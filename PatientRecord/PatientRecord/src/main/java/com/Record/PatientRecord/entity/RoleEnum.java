package com.Record.PatientRecord.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

public enum RoleEnum {
    PATIENT,
    PHYSICIAN,
    ADMIN;
}
