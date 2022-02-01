package com.medicalrecord.medicalrecord.repositories;

import com.medicalrecord.medicalrecord.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
