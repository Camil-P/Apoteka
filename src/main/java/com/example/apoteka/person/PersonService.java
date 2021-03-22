package com.example.apoteka.person;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonByNameSurnameIsOwner(String name, String surname, Boolean isOwner) {
        return personRepository.findByNameAndSurnameAndIsOwner(name, surname, isOwner).stream().findFirst();
    }

    public Optional<Person> getPerson(String name, String surname, Boolean isOwner) {
        return personRepository.findByNameOrSurnameOrIsOwner(name, surname, isOwner).stream().findFirst();
    }

    public Boolean newPerson(Person person){
        try{
            personRepository.save(person);
        }
        catch(IllegalArgumentException e){
            return false;
        }
        return true;
    }

    public Boolean deletePerson(Long id){
        try{
            personRepository.deleteById(id);
        }
        catch(IllegalArgumentException e){
            return false;
        }
        return true;
    }

    public Boolean updatePerson(Person person){
        Person p = personRepository.findById(person.getId());
        try {
            p.setName(person.getName());
            p.setSurname(person.getSurname());
            p.setIsOwner(person.getIsOwner());
            personRepository.save(p);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
