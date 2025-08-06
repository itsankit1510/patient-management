package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsExceotion;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public PatientResponseDTO createPatient(PatientRequestTO patientRequestTO) {
        if(patientRepository.existsByEmail(patientRequestTO.getEmail())) {
            throw new EmailAlreadyExistsExceotion("A patient with this email" + "already exists " + patientRequestTO.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestTO));
        return PatientMapper.toDTO(patient);
    }
}
