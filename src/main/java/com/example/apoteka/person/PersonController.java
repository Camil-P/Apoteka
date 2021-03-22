package com.example.apoteka.person;

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
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/person")
    public Optional<Person> getPerson(
        @RequestParam(value = "name", defaultValue = "") String name,
        @RequestParam(value = "surname", defaultValue = "") String surname,
        @RequestParam(value = "isOwner", defaultValue = "false") Boolean isOwner) {
            if(!name.equals("") && !surname.equals("")){
                return personService.getPersonByNameSurnameIsOwner(name, surname, isOwner).stream().findFirst();
            }
            return personService.getPerson(name, surname, isOwner).stream().findFirst();
    }

    @PostMapping("/person")
    public String newPerson(@RequestBody Person person){
        if(personService.newPerson(person)){
            return "Korisnik uspesno registrovan";
        }
        return "Dodavanje korisnika nije uspelo";
    }

    @DeleteMapping("/person")
    public String deletePerson(@RequestBody long id){
        if(personService.deletePerson(id)) {
            return "Osoba je uspesno uklonjena";
        }
        return "Greska! osoba nije uklonjena ";
    }

    @PutMapping("/person")
    public String updatePerson(@RequestBody Person person){
        if(personService.updatePerson(person)){
            return "Korisnik uspesno izmenjen";
        }
        return "Greska! Korisnik nije izmenjen";
    }
}
