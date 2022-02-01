package com.Record.PatientRecord.controller;

import com.Record.PatientRecord.entity.Appointment;
import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.entity.Physician;
import com.Record.PatientRecord.entity.User;
import com.Record.PatientRecord.service.PatientService;
import com.fasterxml.jackson.core.sym.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
 
    // @PostMapping("/")
    // @PreAuthorize("hasRole('ADMIN')")
    // public void  createPatient(@RequestBody Patient patient) {
    //     patientService.savePatient(patient);
    // }
    @PreAuthorize("hasAnyAuthority('PATIENT')")
    @PostMapping("/{id}")
    public void updatePatient(@PathVariable Long id,@RequestBody User patient){
        patientService.updatePatient(id,patient);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PATIENT')")
    @PostMapping("/createAppointment/{id}")
    public  ResponseEntity<Appointment>  CreateAppointment(@PathVariable Long id, @RequestBody Physician name){
        //  if(patientService.createAppointment(id,name) == null){
             
        //  }
        return  patientService.createAppointment(id,name);

    
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','PATIENT')")
    @GetMapping("/viewAppointment/{id}")
    public  ResponseEntity<List<String>>  ViewAppointment(@PathVariable Long id){
        List<String> p= patientService.viewAppointment(id);
       return new ResponseEntity<>(p,HttpStatus.OK);
    
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','PATIENT')")
    @GetMapping("/searchDoctors/{id}/{name}")
    public List<String> getDoctorsByname( @PathVariable Long id,@PathVariable String name) {
        
        return  patientService.getDoctors(id,name).stream().map((s)->
        s.getFirstName()).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PATIENT')")
    @GetMapping("/searchDoctorsByDate/{id}/{date}")
    public LocalDate getDoctorsByDate( @PathVariable Long id,@PathVariable 
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws ParseException {
        return  patientService.getDoctorsByDate(id,date).getAvailableDate();
        // .stream().map((s)->
        // s.getAvailableDate()).collect(Collectors.toList());
    }

}
