package com.Record.PatientRecord.repository;

import com.Record.PatientRecord.entity.Appointment;
import com.Record.PatientRecord.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findBypatientId(Long id);
    Appointment findByphysicianId(Long id);


}
