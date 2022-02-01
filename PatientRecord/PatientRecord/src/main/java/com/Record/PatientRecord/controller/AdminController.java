package com.Record.PatientRecord.controller;

import com.Record.PatientRecord.entity.Admin;
import com.Record.PatientRecord.entity.Appointment;
import com.Record.PatientRecord.entity.User;
import com.Record.PatientRecord.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Admin>> getAdmins() {
        List<Admin> admins = adminService.getAllAdmin();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
   
    @PostMapping("/")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.addAdmin(admin), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long id) {
        Admin admin = adminService.getAdmin(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody User admin) {
        adminService.updateAdmin(id, admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateAppointment/{id}")
    public ResponseEntity<?> updateAppointement(@PathVariable Long id, @RequestBody Appointment appointment) {
        adminService.updateAppointment(id, appointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllusers/")
    public ResponseEntity<List<User>> getAllUsers() {
       
        return new ResponseEntity<>( adminService.getAllUsers(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<?> deleteAppointement(@PathVariable Long id) {
        adminService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<?> deletePateint(@PathVariable Long id) {
        adminService.DeletePateint(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
