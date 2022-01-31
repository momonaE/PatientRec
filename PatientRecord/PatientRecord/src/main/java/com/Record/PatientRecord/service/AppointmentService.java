package com.Record.PatientRecord.service;

import com.Record.PatientRecord.entity.Appointment;
import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.entity.Physician;
import com.Record.PatientRecord.entity.User;
import com.Record.PatientRecord.repository.AppointmentRepository;
import com.Record.PatientRecord.repository.PatientRepository;
import com.Record.PatientRecord.repository.PhysicianRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.Return;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private static final Logger log = LoggerFactory.getLogger(AppointmentService.class);
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PhysicianService physicianService;


    public ResponseEntity<Appointment> createAppointment(Long id, Physician P) {

        LocalDate date = P.getAvailableDate();
        String name = P.getFirstName();

        Appointment p = new Appointment();
        // if (patientRepository.findById(id)==null) {
        //     System.out.println("yes yes");

        //     return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        // }
         Patient patient = patientRepository.findById(id).get();

        if (getAppointment(patient.getId())!=null) {
            return new ResponseEntity<>( HttpStatus.ALREADY_REPORTED);
        }
        Physician allPhysciansBydate = physicianService.getDoctorsByDate(date);
        List<Physician> allPhysiciandByName = physicianService.getDoctorsByName(name);
        // for(Physician f :allPhysciansBydate){
        //     System.out.println(f.getAvailableDate());   

        // }
        System.out.println(date +" " +name);

     if (allPhysciansBydate.getAvailableDate().equals(date)&& allPhysciansBydate.getFirstName().equals(name)) {
        System.out.println(allPhysciansBydate.getAvailableDate() + " my"+ date +allPhysciansBydate.getFirstName());
        System.out.println(allPhysciansBydate.getAvailableDate() +" " +allPhysciansBydate.getFirstName());

        LocalDate d = allPhysciansBydate.getAvailableDate();
            p.setAppointmentDate(d);
            p.setAppointmentType("emergency");
            p.setPatientId(id);

            System.out.println(allPhysiciandByName.get(0).getId());
            p.setPhysicianId(allPhysiciandByName.get(0).getId());

        } else {
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(appointmentRepository.save(p),HttpStatus.OK);

    }

    public Appointment getAppointment(Long id) {
        return appointmentRepository.findBypatientId(id);
    }

    public Appointment getAppointmentFordoctor(Long id) {
        return appointmentRepository.findByphysicianId(id);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // public List<Physician> getAllPhysician() {
    // return physicianRepository.findAll();
    // }
    public ResponseEntity<Appointment> updateAppointment(Long id, Appointment physician) {
        Optional<Appointment> oldaAppointments = appointmentRepository.findById(id);
        if (oldaAppointments.isPresent()) {
            Appointment oldaAppointment = oldaAppointments.get();
            oldaAppointment.setAppointmentDate(physician.getAppointmentDate());
            oldaAppointment.setAppointmentType(physician.getAppointmentType());
            oldaAppointment.setPhysicianId(physician.getPhysicianId());

            return new ResponseEntity<>(appointmentRepository.save(oldaAppointment), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
