package com.medicalrecord.medicalrecord.controllers;

import com.medicalrecord.medicalrecord.entity.Consultation;
import com.medicalrecord.medicalrecord.services.ConsultationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/medicalRecords/consultations")
public class ConsultationController {
    @Resource
    private ConsultationService consultationService;

    @PostMapping("/")
    public Consultation saveConsultation(@RequestBody Consultation consultation){
        return  consultationService.saveConsultation(consultation);
    }
    @GetMapping("/{id}")
    public Consultation findConsultationById(@PathVariable("id") Long consultationId){
        return consultationService.findConsultationById(consultationId);
    }
    @DeleteMapping("/{id}")
    public void deleteConsultationById(@PathVariable("id") Long consultationId){
       consultationService.deleteConsultationById(consultationId);
    }
    @GetMapping("/all")
    public List<Consultation> getAll(){
        return consultationService.getAll();
    }

}
