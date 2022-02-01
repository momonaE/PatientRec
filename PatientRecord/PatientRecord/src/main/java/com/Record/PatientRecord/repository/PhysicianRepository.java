package com.Record.PatientRecord.repository;

import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.entity.Physician;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    List<Physician> findByfirstName(String physicianName);
   // @Query("Select P from Physician P order by  P.availableDate")
    Physician findByavailableDate(LocalDate avaDate);


}
