package com.Record.Receptionist.controller;

import com.Record.Receptionist.entity.Receptionist;
import com.Record.Receptionist.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {
    @Autowired
    private ReceptionistService receptionistService;

    @GetMapping("/")
    public List<Receptionist> getAllAppointments(){
        return  receptionistService.getAllAppointement();
    }
    @PostMapping("/")
    public void setAppointments(Receptionist receptionist){
         receptionistService.setAppointement(receptionist);
    }
    @GetMapping("/{id}")
    public  Receptionist getAppointment(@PathVariable Long id){
        return receptionistService.getAppointement(id);
    }
    @PostMapping("/{id}")
    public void updateAppointment(@PathVariable Long id,Receptionist receptionist){
         receptionistService.updatePatientById(receptionist);
    }
}
