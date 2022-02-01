package com.medicalrecord.medicalrecord.controllers;


import com.medicalrecord.medicalrecord.services.MedicalRecordService;
import com.medicalrecord.medicalrecord.entity.MedicalRecord;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordController {
    @Resource
    private MedicalRecordService medicalRecordService;

    @PostMapping("/save")
    public MedicalRecord saveMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.saveMedicalRecord(medicalRecord);
    }
    @GetMapping("/{id}")
    public MedicalRecord findMedicalRecordById(@PathVariable("id") Long medicalRecordId){
        return medicalRecordService.findMedicalRecordById(medicalRecordId);
    }
    @DeleteMapping("/{id}")
    public void deleteMedicalRecordById(@PathVariable("id") Long medicalId){
        medicalRecordService.deleteMedicalRecordById(medicalId);
    }
    @GetMapping("/all")
    public List<MedicalRecord> getAll(){
        return medicalRecordService.getAll();
    }
}
