package com.example.apoteka.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByCityAndCountry(@Param("city") String name, @Param("country") String surname);
    Optional<Location> findByCityOrCountry(@Param("city") String name, @Param("country") String surname);
    Location findById(long id);
}
