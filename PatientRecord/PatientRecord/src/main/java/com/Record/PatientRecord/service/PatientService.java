package com.Record.PatientRecord.service;

import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    /* save patient*/
    public void savePatient(Patient patient){
        patientRepository.save(patient);

    }
    /* get all patients */
    public List<Patient> getAllPateints(){
          return patientRepository.findAll();
    }

    /*get patient By id*/
    public Patient getPatientById(Long id ){
        return patientRepository.findById(id).get();
    }

    /*Update patient By id*/
    public  void updatePatientById(Patient patient){
         patientRepository.save(patient);
    }

    /*delete patient By id*/
    public void deletePatientById(Long id){
        patientRepository.deleteById(id);
    }

}
