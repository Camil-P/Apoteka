package com.example.apoteka.pharmacy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.apoteka.location.Location;
import com.example.apoteka.medicine.Medicine;
import com.example.apoteka.person.Person;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    @OneToOne
    @JoinColumn(name = "owner_id")
    @RestResource(path = "pharmacyOwner", rel="owner")
    private Person owner;

    @OneToMany
    private List<Person> workers;

    @OneToMany
    private List<Medicine> medicines;

    public Long getId() {
        return id;
    }    

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<Person> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Person> workers) {
        this.workers = workers;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicine) {
        this.medicines = medicine;
    }
}
