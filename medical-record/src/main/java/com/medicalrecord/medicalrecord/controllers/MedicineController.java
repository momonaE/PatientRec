package com.medicalrecord.medicalrecord.controllers;

import com.medicalrecord.medicalrecord.entity.Medicine;
import com.medicalrecord.medicalrecord.services.MedicineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/medicalRecords/medicines")
public class MedicineController {
    @Resource
    private MedicineService medicineService;
    @PostMapping("/")
    public Medicine addMedicine(@RequestBody Medicine medicine){
        return medicineService.addMedicine(medicine);
    }
    @GetMapping("/{id}")
    public Medicine getMedicine(@PathVariable("id") Long medicineId){
        return medicineService.getMedicine(medicineId);
    }
    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable("id") Long medicineId){
        medicineService.deleteMedicine(medicineId);
    }
    @GetMapping("/findMedicine/{name}")
    public List<String> findByMedicineName(@PathVariable("name") String name){
        return medicineService.findByMedicineName(name);
    }
    @GetMapping("/all")
    public List<Medicine> getAll(@PathVariable("all")@RequestBody Medicine medicine){
        return medicineService.getAll();
    }
    @PutMapping("/{id}")
    public Medicine update(@RequestBody@PathVariable("id") Long medicineId, Medicine medicine){
        if(medicineId != medicine.getId()){
            throw new IllegalArgumentException();
        }
        return medicineService.update(medicine);
    }

}
