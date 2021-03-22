package com.example.apoteka.jwtAuthentication;

import com.example.apoteka.person.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jwtAuthenticationRepository extends JpaRepository<Person, Long> {
    
}
