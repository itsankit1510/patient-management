package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatient(@PathVariable("id") UUID id) {
        PatientResponseDTO patient = patientService.getPatient(id);
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestTO patientRequestTO) {
        PatientResponseDTO patient = patientService.createPatient(patientRequestTO);
        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("id") UUID id, @Valid @RequestBody PatientRequestTO patientRequestTO) {
        PatientResponseDTO patient = patientService.updatePatient(id, patientRequestTO);
        return ResponseEntity.ok().body(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable("id") UUID id) {
        PatientResponseDTO patient = patientService.deletePatient(id);
        return ResponseEntity.ok().body(patient);
    }

}
