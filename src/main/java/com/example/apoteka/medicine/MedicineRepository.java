package com.example.apoteka.medicine;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{
    Optional<Medicine> findByName(@Param("name") String name);
    Medicine findById(long id);
}
