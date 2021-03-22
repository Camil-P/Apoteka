package com.example.apoteka.pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    // Optional<Pharmacy> findByPharmacyLocationAndPharmacyMedicine(@Param("pharmacyLocation") String pharmacyLocation, @Param("pharmacyMedicine") String pharmacyMedicine);
    // Optional<Pharmacy> findByPharmacyLocationOrPharmacyMedicine(@Param("pharmacyLocation") String pharmacyLocation, @Param("pharmacyMedicine") String pharmacyMedicine);
    Pharmacy findById(long id);
}
