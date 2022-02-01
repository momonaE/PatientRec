package com.Record.PatientRecord.service;

import com.Record.PatientRecord.entity.Admin;
import com.Record.PatientRecord.entity.Appointment;
import com.Record.PatientRecord.entity.Physician;
import com.Record.PatientRecord.entity.User;
import com.Record.PatientRecord.repository.AdminRepository;
import com.Record.PatientRecord.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.Return;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
@Service
@RequiredArgsConstructor
public class AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getAdmin(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public void updateAdmin(Long id, User admin) {
        User oldadmin = userRepo.findByUsername(admin.getUsername());
        
            if(oldadmin!=null){
                oldadmin.setFirstName(admin.getFirstName());
            oldadmin.setLastName(admin.getLastName());
            oldadmin.setDob(admin.getDob());
            oldadmin.setEmail(admin.getEmail());
            oldadmin.setUsername(admin.getUsername());
            oldadmin.setPassword(passwordEncoder.encode(admin.getPassword()));
            userRepo.save(oldadmin);
            }
            Admin oldadmn1 = adminRepository.findById(id).get();
            if(oldadmn1!=null){
            System.out.println("is this error"+admin.getUsername());
            oldadmn1.setFirstName(oldadmin.getFirstName());
            oldadmn1.setLastName(oldadmin.getLastName());
            oldadmn1.setDob(oldadmin.getDob());
            oldadmn1.setEmail(oldadmin.getEmail());
            oldadmn1.setUsername(oldadmin.getUsername());
            oldadmn1.setPassword(passwordEncoder.encode(oldadmin.getPassword()));
            adminRepository.save(oldadmn1);
            }
    
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public void deleteAppointment(Long id) {
        appointmentService.deleteAppointment(id);
        
    }
    public ResponseEntity<Appointment> updateAppointment(Long id,Appointment appoint) {
      //  return new ResponseEntity<>(appointmentService.updateAppointment(id,appoint),HttpStatus.OK); 
         return appointmentService.updateAppointment(id,appoint);
    }
   // admin can see all doctors
    public User getPhysician(Long id) {
        return userRepo.findById(id).orElse(null);
    }
   public List<User> getAllUsers() {
    return userRepo.findAll();
    }
    public void DeletePateint (long id){
         userRepo.deleteById(id);
         log.info("user deleted");
    }


}
