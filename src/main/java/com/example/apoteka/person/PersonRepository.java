package com.example.apoteka.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByNameAndSurnameAndIsOwner(@Param("name") String name, @Param("surname") String surname, @Param("isOwner") Boolean isOwner);
    Optional<Person> findByNameOrSurnameOrIsOwner(@Param("name") String name, @Param("surname") String surname, @Param("isOwner") Boolean isOwner);
    Person findById(long id);
}
