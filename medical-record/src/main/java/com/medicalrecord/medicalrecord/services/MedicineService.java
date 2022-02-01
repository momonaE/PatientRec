package com.medicalrecord.medicalrecord.services;

import com.medicalrecord.medicalrecord.entity.Medicine;
import com.medicalrecord.medicalrecord.repositories.MedicineRepository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicineService {
    @Resource
    private MedicineRepository medicineRepository;

    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public Medicine getMedicine(Long medicineId) {
        return medicineRepository.findById(medicineId).orElse(null);
    }

    public void deleteMedicine(Long medicineId) {
        medicineRepository.deleteById(medicineId);
    }
    public List<String> findByMedicineName(String name){
        return medicineRepository.findByName(name).stream().map(s->s.getName()).collect(Collectors.toList());
    }
    public List<Medicine> getAll() {
        List<Medicine> medicines = medicineRepository.findAll();
        return medicines;
    }
    public Medicine update(Medicine medicine) { return medicineRepository.save(medicine);  }
}
