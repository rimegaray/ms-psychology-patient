package com.example.demo.controller;

import com.example.demo.repository.dto.PatientDto;
import com.example.demo.repository.entity.PatientEntity;
import com.example.demo.service.PatientService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public PatientDto addPatient (@RequestBody PatientDto patientDto) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFullName(patientDto.getFullName());
        patientEntity.setDni(patientDto.getDni());
        patientEntity.setAge(patientDto.getAge());
        patientEntity.setContactNumber(patientDto.getContactNumber());
        patientEntity.setAddress(patientDto.getAddress());
        patientEntity.setEmail(patientDto.getEmail());
        patientEntity.setOccupation(patientDto.getOccupation());
        patientEntity.setDateOfAdmission(patientDto.getDateOfAdmission());
        patientEntity.setLifeStory(patientDto.getLifeStory());
        patientEntity.setObservations(patientDto.getObservations());
        return patientService.addPatient(patientEntity);
    }

    @GetMapping("/{id}")
    public PatientEntity getById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }
    @PutMapping("/{id}")
    public PatientEntity update(@PathVariable int id, @RequestBody PatientEntity patientEntity) {
        PatientEntity existingEntity = patientService.getPatientById(id);
        if (existingEntity == null) {
            return null;
        }
        patientEntity.setIdPatient(id);
        return patientService.updatePatient(id, patientEntity);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        patientService.deletePatient(id);
    }
    @GetMapping
    public List<PatientDto> getAll() {
        List<PatientEntity> raton = patientService.getAll();
        return raton.stream()
                .map(this::convertToDtoPatient)
                .collect(Collectors.toList());
    }
    private PatientDto convertToDtoPatient (PatientEntity patientEntity) {
        PatientDto patientDto = new PatientDto();
        patientDto.setFullName(patientEntity.getFullName());
        patientDto.setDni(patientEntity.getDni());
        patientDto.setAge(patientEntity.getAge());
        patientDto.setContactNumber(patientEntity.getContactNumber());
        patientDto.setAddress(patientEntity.getAddress());
        patientDto.setEmail(patientEntity.getEmail());
        patientDto.setOccupation(patientEntity.getOccupation());
        patientDto.setDateOfAdmission(patientEntity.getDateOfAdmission());
        patientDto.setLifeStory(patientEntity.getLifeStory());
        patientDto.setObservations(patientEntity.getObservations());
        return patientDto;
    }

}
