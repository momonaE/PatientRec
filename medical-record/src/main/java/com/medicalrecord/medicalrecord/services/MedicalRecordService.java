package com.medicalrecord.medicalrecord.services;

import com.medicalrecord.medicalrecord.repositories.MedicalRecordRepository;
import com.medicalrecord.medicalrecord.entity.Consultation;
import com.medicalrecord.medicalrecord.entity.MedicalRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MedicalRecordService {
    @Resource
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }
    public MedicalRecord findMedicalRecordById(Long medicalRecordId) {
        return medicalRecordRepository.findById(medicalRecordId).get();
    }
    public void deleteMedicalRecordById(Long medicalId) {
        medicalRecordRepository.deleteById(medicalId);
    }
    public List<MedicalRecord> getAll(){
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        return medicalRecords;
    }
}
