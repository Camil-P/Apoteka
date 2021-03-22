package com.example.apoteka.medicine;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedicineController {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineController(MedicineRepository medicineRepository){
        this.medicineRepository = medicineRepository;
    }

    @GetMapping("/medicines")
    public List<Medicine> getMedicines() {
        return medicineRepository.findAll();
    }

    @GetMapping("/medicine")
    public Optional<Medicine> getMedicine(
        @RequestParam(value = "name", defaultValue = "") String name){
            return medicineRepository.findByName(name).stream().findFirst();
    }

    @PostMapping("/medicine")
    public String newMedicine(@RequestBody Medicine medicine){
        try {
            medicineRepository.save(medicine);
            return "Lek uspesno unet";
        } catch (IllegalArgumentException e) {
            return "Greska! Lek nije unet u bazu " + e;
        }
    }

    @DeleteMapping("/medicine")
    public String deleteMedicine(@RequestParam(value = "id") Long id){
        try {
            medicineRepository.deleteById(id);
            return "Lek uspesno uklonjen";
        } catch (IllegalArgumentException e) {
            return "Greska! Lek nije uklonjen " + e;
        }
    }

    @PutMapping("/medicine")
    public String updateMedicine(@RequestBody Medicine medicine){
        Medicine m = medicineRepository.findById(medicine.getId());
        try {
            m.setName(medicine.getName());
            m.setDose(medicine.getDose());
            m.setReplacement(medicine.getReplacement());
            medicineRepository.save(m);
            return "Lek uspesno izmenjen";
        } catch (Exception e) {
            return "Greska! Lek nije izmenjen izmenjen " + e;
        }
    }
}
