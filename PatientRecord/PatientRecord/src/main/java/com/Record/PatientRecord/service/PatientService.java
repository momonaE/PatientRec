package com.Record.PatientRecord.service;

import com.Record.PatientRecord.entity.Admin;
import com.Record.PatientRecord.entity.Appointment;
import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.entity.Physician;
import com.Record.PatientRecord.entity.User;
import com.Record.PatientRecord.repository.AppointmentRepository;
import com.Record.PatientRecord.repository.PatientRepository;
import com.Record.PatientRecord.repository.UserRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor

public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PhysicianService physicianService;

    @Autowired
    private   AppointmentService  appointmentService;

    @Autowired
    private UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;
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
    public void updatePatient(Long id, User Patient) {
      
        User oldPatient = userRepo.findByUsername(Patient.getUsername());
        if(oldPatient!=null){
        oldPatient.setFirstName(Patient.getFirstName());
        oldPatient.setLastName(Patient.getLastName());
        oldPatient.setDob(Patient.getDob());
        oldPatient.setEmail(Patient.getEmail());
        oldPatient.setUsername(Patient.getUsername());
        oldPatient.setPassword(passwordEncoder.encode(Patient.getPassword()));
        userRepo.save(oldPatient);
        }
        Patient oldPatient1 = patientRepository.findById(id).get();
        if(oldPatient1!=null){
        System.out.println("is this error"+Patient.getUsername());
        oldPatient1.setFirstName(Patient.getFirstName());
        oldPatient1.setLastName(Patient.getLastName());
        oldPatient1.setDob(Patient.getDob());
        oldPatient1.setEmail(Patient.getEmail());
        oldPatient1.setUsername(Patient.getUsername());
        oldPatient1.setPassword(passwordEncoder.encode(Patient.getPassword()));
        patientRepository.save(oldPatient1);
        }
    }

    /*delete patient By id*/
    public void deletePatientById(Long id){
        patientRepository.deleteById(id);
    }
 
    public List<Physician> getDoctors(Long id, String name) {
       return physicianService.getDoctorsByName(name);
    }
    public Physician getDoctorsByDate(Long id, LocalDate date) {
        return  physicianService.getDoctorsByDate(date);
    }
 
    public ResponseEntity<Appointment> createAppointment(Long id, Physician P) {
   
         return appointmentService.createAppointment(id,P);
      
    }
    public List<String> viewAppointment(Long id) {
         
     List<String>   appointment = new ArrayList<>();
    Appointment p =  appointmentService.getAppointment(id);
    String doctorname= physicianService.getPhysician(p.getPhysicianId()).getFirstName();
    appointment.add(p.getAppointmentDate().toString());
    appointment.add(p.getAppointmentType());
        appointment.add(doctorname);
        return appointment;


    }

}
