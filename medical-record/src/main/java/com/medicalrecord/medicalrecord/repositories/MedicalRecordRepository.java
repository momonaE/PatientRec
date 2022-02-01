package com.medicalrecord.medicalrecord.repositories;

import com.medicalrecord.medicalrecord.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,  Long> {

}
