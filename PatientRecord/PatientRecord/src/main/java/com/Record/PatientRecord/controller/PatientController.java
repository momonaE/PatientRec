package com.Record.PatientRecord.controller;

import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients/")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public List<Patient> getAllPatients(){
        return  patientService.getAllPateints();
    }
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id){
        return  patientService.getPatientById(id);
    }
    @PostMapping("/")
    public void updatePatient(Patient patient){
        patientService.updatePatientById(patient);
    }
    @DeleteMapping("/{id}")
    public void  deletePatient(@PathVariable Long id){
         patientService.deletePatientById(id);
    }

}
