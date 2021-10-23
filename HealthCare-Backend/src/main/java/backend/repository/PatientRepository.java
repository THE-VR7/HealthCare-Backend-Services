package backend.repository;

import org.springframework.stereotype.Repository;

import backend.models.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

}
