package com.Record.PatientRecord.controller;

import com.Record.PatientRecord.entity.Patient;
import com.Record.PatientRecord.entity.Physician;
import com.Record.PatientRecord.entity.User;
import com.Record.PatientRecord.service.PatientService;
import com.Record.PatientRecord.service.PhysicianService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("physicians/")
public class PhysicianController {
    @Autowired
    private PhysicianService physicianService;
    @Autowired
    private RestTemplate restTemplate;

    // @GetMapping("/")
    // @Secured("PHYSICIAN")
    // public List<Physician> getAllPhysician() {
    // System.out.println("get physician");
    // return physicianService.getAllPhysician();
    // }

    @GetMapping("/{id}")
    public Physician getPhysician(@PathVariable Long id) {
        return physicianService.getPhysician(id);
    }

    // @PostMapping("/")
    // @Secured("PHYSICIAN")
    // public void addPhysician(@RequestBody Physician physician) {
    // physicianService.addPhysician(physician);
    // }

    @PutMapping("/{id}")
    public void updatePhysician(@PathVariable Long id, @RequestBody User physician) {
        physicianService.updatePhysician(id, physician);
    }

    @PutMapping("/addPhysicianSepcialist/{id}")
    public void addPhysicianSepcialist(@PathVariable Long id, @RequestBody Physician doctorSpecialist) {
        System.out.println(doctorSpecialist);
        physicianService.addPhysicianSepcialist(id, doctorSpecialist.getDoctorSpecialist());
    }

    @PutMapping("/addAvailableDate/{id}")
    public ResponseEntity<?> addAvailableDate(@PathVariable Long id, @RequestBody Physician availabeDate) {
        return physicianService.addPhysicianAvailableDate(id, availabeDate.getAvailableDate());
    }

    @GetMapping("/viewAppointment/{id}")
    public ResponseEntity<List<String>> ViewAppointment(@PathVariable Long id) {
        List<String> p = physicianService.viewAppointment(id);
        return new ResponseEntity<>(p, HttpStatus.OK);

    }

    @PostMapping("/CreateMedicalRecord/{id}")
    public String createMedicalRecord(@PathVariable Long id, @RequestBody User p) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/spring-rest/foos";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
            return null;
            
    }

    @GetMapping("/viewMedicalRecord/{id}")
    public ResponseEntity<?> viewRecord(@PathVariable Long id ){
            return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
