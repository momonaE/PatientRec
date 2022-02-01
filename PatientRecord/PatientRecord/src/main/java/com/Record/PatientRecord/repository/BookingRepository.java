package com.Record.PatientRecord.repository;

import com.Record.PatientRecord.entity.Appointment;
import com.Record.PatientRecord.entity.Book;
import com.Record.PatientRecord.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Book, Long> {
}
