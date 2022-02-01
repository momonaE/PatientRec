package com.medicalrecord.medicalrecord.repositories;

import com.medicalrecord.medicalrecord.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long > {

}
