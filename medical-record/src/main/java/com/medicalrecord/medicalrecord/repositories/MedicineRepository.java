package com.medicalrecord.medicalrecord.repositories;

import  com.medicalrecord.medicalrecord.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByName(String name);
}
