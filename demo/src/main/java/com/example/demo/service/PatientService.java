package com.example.demo.service;


import com.example.demo.repository.dao.PatientDao;
import com.example.demo.repository.dto.PatientDto;
import com.example.demo.repository.entity.PatientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientDao patientDao;

    public PatientDto addPatient (PatientDto patientDto) {
        PatientEntity patientResult = patientDao.save(convertToEntityPatient(patientDto));
        return convertPatientEntityToDtoPatient(patientResult);
    }
    public List<PatientDto> getAll() {
        List<PatientEntity> patientEntities = patientDao.findAll();
        return patientEntities.stream()
                .map(this::convertPatientEntityToDtoPatient)
                .collect(Collectors.toList());
    }
    public PatientDto getPatientById(int patientId) {
        return convertPatientEntityToDtoPatient(patientDao.findById(patientId).orElse(null));
    }
    public PatientDto updatePatient(int idPatient, PatientDto pacientUpdate) {
        PatientEntity existingPatient = patientDao.findById(idPatient).orElse(null);
        if (existingPatient != null) {
            existingPatient.setFullName(pacientUpdate.getFullName());
            existingPatient.setDni(pacientUpdate.getDni());
            existingPatient.setAge(pacientUpdate.getAge());
            existingPatient.setContactNumber(pacientUpdate.getContactNumber());
            existingPatient.setAddress(pacientUpdate.getAddress());
            existingPatient.setEmail(pacientUpdate.getEmail());
            existingPatient.setOccupation(pacientUpdate.getOccupation());
            existingPatient.setDateOfAdmission(pacientUpdate.getDateOfAdmission());
            existingPatient.setLifeStory(pacientUpdate.getLifeStory());
            existingPatient.setObservations(pacientUpdate.getObservations());
        }
        return convertPatientEntityToDtoPatient(patientDao.save(existingPatient));
    }

    public void deletePatient (int idPatient) {
        patientDao.deleteById(idPatient);
    }

    private PatientDto convertPatientEntityToDtoPatient (PatientEntity patientEntity) {
        return PatientDto.builder().fullName(patientEntity.getFullName())
									.dni(patientEntity.getDni())
									.age(patientEntity.getAge())
									.contactNumber(patientEntity.getContactNumber())
									.address(patientEntity.getAddress())
									.email(patientEntity.getEmail())
									.occupation(patientEntity.getOccupation())
									.dateOfAdmission(patientEntity.getDateOfAdmission())
									.lifeStory(patientEntity.getLifeStory())
									.observations(patientEntity.getObservations())
									.build();
    }
    private PatientEntity convertToEntityPatient (PatientDto patientDto) {
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
        return patientEntity;
    }
    
}
