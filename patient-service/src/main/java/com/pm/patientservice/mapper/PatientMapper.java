package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientResponseDTO;
    }

    public static Patient toModel(PatientRequestTO patientRequestTO) {
        Patient patient = new Patient();
        patient.setName(patientRequestTO.getName());
        patient.setEmail(patientRequestTO.getEmail());
        patient.setAddress(patientRequestTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestTO.getRegisteredDate()));
        return patient;
    }
}
