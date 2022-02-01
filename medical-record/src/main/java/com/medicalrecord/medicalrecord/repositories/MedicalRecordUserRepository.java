package com.medicalrecord.medicalrecord.repositories;

import com.medicalrecord.medicalrecord.entity.MedicalRecordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MedicalRecordUserRepository extends JpaRepository<MedicalRecordUser, Long> {
    MedicalRecordUser findByName(String userName);
}
