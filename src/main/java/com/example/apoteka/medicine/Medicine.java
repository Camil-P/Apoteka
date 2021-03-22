package com.example.apoteka.medicine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float dose;

    @OneToOne
    private Medicine replacement;

    public Medicine(){}
    
    public Medicine(long id, String name, Float dose, Medicine replacement){
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.replacement = replacement;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDose() {
        return dose;
    }

    public void setDose(Float dose) {
        this.dose = dose;
    }

    public Medicine getReplacement() {
        return replacement;
    }

    public void setReplacement(Medicine replacement) {
        this.replacement = replacement;
    }
}
