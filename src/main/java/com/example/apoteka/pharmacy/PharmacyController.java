package com.example.apoteka.pharmacy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PharmacyController {
    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyController(PharmacyRepository pharmacyRepository){
        this.pharmacyRepository = pharmacyRepository;
    }

    @GetMapping("/pharmacies")
    public List<Pharmacy> getPharmacies() {
        return pharmacyRepository.findAll();
    }

    // @GetMapping("/pharmacy")
    // public Pharmacy getPharmacy(
    //     @RequestParam(value = "pharmacyLocation", defaultValue = "") String pharmacyLocation,
    //     @RequestParam(value = "pharmacyMedicine", defaultValue = "") String pharmacyMedicine) {
    //         List<Pharmacy> pharmacyList = pharmacyRepository.findAll();
    //         if(!pharmacyLocation.equals("") && !pharmacyMedicine.equals("")){
    //             var p = pharmacyList.stream().filter(x -> x.getLocation().getAddress().equals(pharmacyLocation) && 
    //             x.getMedicines().stream().filter(m -> m.getName().equals(pharmacyMedicine)).count() > 0);
    //             if(p.count() > 0){
    //                 return (Pharmacy) p.toArray()[0];
    //             }
    //         }
    //         var p = pharmacyList.stream().filter(x -> x.getLocation().getAddress().equals(pharmacyLocation) || 
    //             x.getMedicines().stream().filter(m -> m.getName().equals(pharmacyMedicine)).count() > 0);
    //             if(p.count() > 0){
    //                 return (Pharmacy) p.toArray()[0];
    //             }
    //         return new Pharmacy();
    // }

    @PostMapping("/pharmacy")
    public String newLocation(@RequestBody Pharmacy pharmacy){
        try {
            pharmacyRepository.save(pharmacy);
            return "Apoteka uspesno uneta";
        } catch (IllegalArgumentException e) {
            return "Apoteka uspesno uneta " + e;
        }
    }

    @DeleteMapping("/pharmacy")
    public String deleteLocation(@RequestParam(value = "id") Long id){
        try {
            pharmacyRepository.deleteById(id);
            return "Apoteka uspesno uklonjena";
        } catch (IllegalArgumentException e) {
            return "Greska! Apoteka nije uklonjena " + e;
        }
    }

    @PutMapping("/pharmacy")
    public String updateLocation(@RequestBody Pharmacy pharmacy){
        Pharmacy p = pharmacyRepository.findById((long) pharmacy.getId());
        try {
            p.setLocation(pharmacy.getLocation());
            p.setOwner(pharmacy.getOwner());
            p.setWorkers(pharmacy.getWorkers());
            p.setMedicines(pharmacy.getMedicines());
            pharmacyRepository.save(p);
            return "Apoteka uspesno izmenjena";
        } catch (Exception e) {
            return "Greska! Apoteka nije izmenjen izmenjena " + e;
        }
    }
}
