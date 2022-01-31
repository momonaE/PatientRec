package com.Record.PatientRecord.repository;
import com.Record.PatientRecord.entity.Admin;
import com.Record.PatientRecord.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, Long> {
}