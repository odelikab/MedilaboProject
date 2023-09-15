package com.medilabo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medilabo.domain.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
