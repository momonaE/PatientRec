package com.Record.Receptionist.service;

import com.Record.Receptionist.entity.Receptionist;
import com.Record.Receptionist.repository.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptionistService {
    @Autowired
    private ReceptionistRepository receptionistRepository;

    public List<Receptionist> getAllAppointement(){
        return receptionistRepository.findAll();

    }
    public Receptionist getAppointement(Long id){
        return receptionistRepository.findById(id).get();

    }
    public void setAppointement(Receptionist receptionist){
        receptionistRepository.save(receptionist);
    }
    public void deleteAppointementById(Long id){
        receptionistRepository.deleteById(id);
    }
    /*Update patient By id*/
    public  void updatePatientById(Receptionist receptionist){
        receptionistRepository.save(receptionist);
    }
}
