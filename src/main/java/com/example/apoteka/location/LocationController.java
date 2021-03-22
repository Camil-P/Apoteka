package com.example.apoteka.location;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationController(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    @GetMapping("/location")
    public Optional<Location> getLocation(
        @RequestParam(value = "city", defaultValue = "") String city,
        @RequestParam(value = "country", defaultValue = "") String country) {
            if(!city.equals("") && !country.equals("")){
                return locationRepository.findByCityAndCountry(city, country).stream().findFirst();
            }
            return locationRepository.findByCityOrCountry(city, country).stream().findFirst();
    }

    @PostMapping("/location")
    public String newLocation(@RequestBody Location location){
        try {
            locationRepository.save(location);
            return "Lokacija uspesno uneta";
        } catch (IllegalArgumentException e) {
            return "Lokacija uspesno uneta " + e;
        }
    }

    @DeleteMapping("/location")
    public String deleteLocation(@RequestParam(value = "id") Long id){
        try {
            locationRepository.deleteById(id);
            return "Lek uspesno uklonjen";
        } catch (IllegalArgumentException e) {
            return "Greska! Lek nije uklonjen " + e;
        }
    }

    @PutMapping("/location")
    public String updateLocation(@RequestBody Location location){
        Location l = locationRepository.findById(location.getId());
        try {
            l.setCity(location.getCity());
            l.setCountry(location.getCountry());
            l.setAddressNumber(location.getAddressNumber());
            l.setAddress(location.getAddress());
            locationRepository.save(l);
            return "Lek uspesno izmenjen";
        } catch (Exception e) {
            return "Greska! Lek nije izmenjen izmenjen " + e;
        }
    }
}