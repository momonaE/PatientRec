package com.medicalrecord.medicalrecord.services;

import com.medicalrecord.medicalrecord.entity.Prescription;
import com.medicalrecord.medicalrecord.repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PrescriptionService {
    @Resource
    private PrescriptionRepository prescriptionRepository;

    public Prescription savePrescription(Prescription prescription){
        return prescriptionRepository.save(prescription);
    }

    public Prescription findPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findById(prescriptionId).get();
    }

    public List<Prescription> getAll() {
        List<Prescription> prescriptions= prescriptionRepository.findAll();
        return prescriptions;
    }

    public Prescription update(Prescription p) {
        return prescriptionRepository.save(p);
    }
}
