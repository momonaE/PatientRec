package com.medicalrecord.medicalrecord.controllers;

import  com.medicalrecord.medicalrecord.entity.Prescription;
import com.medicalrecord.medicalrecord.services.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/medicalRecords/consultations/prescription")
public class PrescriptionController {
    @Resource
    private PrescriptionService prescriptionService;

    @PostMapping("/{add}")
    public Prescription savePrescription(@PathVariable("add")@RequestBody Prescription prescription){
        return prescriptionService.savePrescription(prescription);
    }
    @GetMapping("/{id}")
    public Prescription findPrescriptionById(@PathVariable("id") Long prescriptionId){
        return prescriptionService.findPrescriptionById(prescriptionId);
    }
    @GetMapping("/{all}")
    public List<Prescription> getAll(@PathVariable("all")@RequestBody Prescription prescription){
        return prescriptionService.getAll();
    }
    @PutMapping("/{id}")
    public Prescription update(@RequestBody@PathVariable("id") Long prescriptionId, Prescription p){
        if(prescriptionId != p.getId()){ throw new IllegalArgumentException();}
        return prescriptionService.update(p);
    }
}
