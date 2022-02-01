package com.Record.PatientRecord.service;

import com.Record.PatientRecord.entity.Appointment;
import com.Record.PatientRecord.entity.Physician;
import com.Record.PatientRecord.entity.Role;
import com.Record.PatientRecord.entity.User;
import com.Record.PatientRecord.repository.AppointmentRepository;
import com.Record.PatientRecord.repository.PatientRepository;
import com.Record.PatientRecord.repository.PhysicianRepository;
import com.Record.PatientRecord.repository.UserRepository;
import com.Record.PatientRecord.service.UtilHelper.PhysicianHelper;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhysicianService {
    private static final Logger log = LoggerFactory.getLogger(PhysicianService.class);
    @Autowired
    private PhysicianRepository physicianRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public Physician addPhysician(Physician physician) {
    System.out.println("this is id"+physician.getId());
    return physicianRepository.save(physician);
    }

    public Physician getPhysician(Long id) {
        return physicianRepository.findById(id).orElse(null);
    }
  public List<User> getAllPhysician() {
    return userRepo.findAll();
    }
  
    public void updatePhysician(Long id, User physician) {
      
        User oldphysician = userRepo.findByUsername(physician.getUsername());
        if(oldphysician!=null){
        oldphysician.setFirstName(physician.getFirstName());
        oldphysician.setLastName(physician.getLastName());
        oldphysician.setDob(physician.getDob());
        oldphysician.setEmail(physician.getEmail());
        oldphysician.setUsername(physician.getUsername());
        oldphysician.setPassword(passwordEncoder.encode(physician.getPassword()));
        userRepo.save(oldphysician);
        }
        Physician oldphysician1 = physicianRepository.findById(id).get();
        if(oldphysician1!=null){
        System.out.println("is this error"+physician.getUsername());
        oldphysician1.setFirstName(physician.getFirstName());
        oldphysician1.setLastName(physician.getLastName());
        oldphysician1.setDob(physician.getDob());
        oldphysician1.setEmail(physician.getEmail());
        oldphysician1.setUsername(physician.getUsername());
        oldphysician1.setPassword(passwordEncoder.encode(physician.getPassword()));
        physicianRepository.save(oldphysician1);
        }
    }

    public ResponseEntity<?> addPhysicianSepcialist(Long id, String doctorSpecialist) {

        Physician oldphysician = physicianRepository.findById(id).get();
        if(oldphysician!=null){
            oldphysician.setDoctorSpecialist(doctorSpecialist);
            physicianRepository.save(oldphysician);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
        

    }

    public ResponseEntity<?> addPhysicianAvailableDate(Long id, LocalDate date) {
        Optional<Physician> oldphysician = physicianRepository.findById(id);
        System.out.println("am i getting thsi"+oldphysician);
        if (oldphysician.isPresent()) {
            oldphysician.get().setAvailableDate(date);
            System.out.println("GER" + oldphysician.get().getAvailableDate());
            // .save(oldphysician.get()
            return new ResponseEntity<>(physicianRepository.save(oldphysician.get()), HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    public List<Physician> getDoctorsByName(String name) {

        return physicianRepository.findByfirstName(name);

    }

    public Physician getDoctorsByDate(LocalDate date) {
        
        return physicianRepository.findByavailableDate(date);

    }

    public List<String> viewAppointment(Long id) {
        List<String> appointment = new ArrayList<>();
        Appointment p = appointmentRepository.findByphysicianId(id);
        if(p!=null){
        String Patientname = patientRepository.findById(p.getPatientId()).get().getFirstName();
        System.out.println(Patientname);
        appointment.add(p.getAppointmentDate().toString());
        appointment.add(p.getAppointmentType());
        appointment.add(Patientname);
        return appointment;
        }else{
            log.info("no appointment found");
            return null;
        }
       
    }

}
