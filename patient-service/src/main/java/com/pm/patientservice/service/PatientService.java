package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().
                map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO getPatient(UUID id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with given id" + id));
        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO createPatient(PatientRequestTO patientRequestTO) {
        if(patientRepository.existsByEmail(patientRequestTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email" + "already exists " + patientRequestTO.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestTO));
        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestTO patientRequestTO) {
       Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with given id" + id));
        if(patientRepository.existsByEmail(patientRequestTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email" + "already exists " + patientRequestTO.getEmail());
        }
        patient.setName(patientRequestTO.getName());
        patient.setAddress(patientRequestTO.getAddress());
        patient.setEmail(patientRequestTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestTO.getDateOfBirth()));
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public PatientResponseDTO deletePatient(UUID id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with given id" + id));
        patientRepository.delete(patient);
        return PatientMapper.toDTO(patient);
    }
}
