package com.medicalrecord.medicalrecord.services;

import com.medicalrecord.medicalrecord.entity.Consultation;
import com.medicalrecord.medicalrecord.repositories.ConsultationRepository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConsultationService {
    @Resource
    private ConsultationRepository consultationRepository;

    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
    public Consultation findConsultationById(Long consultationId) {
        return consultationRepository.findById(consultationId).get();
    }

    public void deleteConsultationById(Long consultationId) {
        consultationRepository.deleteById(consultationId);
    }

    public List<Consultation> getAll(){
        List<Consultation> consultations = consultationRepository.findAll();
        return consultations;
    }
}
