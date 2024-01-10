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

    public PatientDto addPatient (PatientEntity patientEntity) {
        PatientEntity patientResult = patientDao.save(patientEntity);
        return convertToDtoPatient(patientResult);
    }
    public List<PatientDto> listPatients() {
        List<PatientEntity> products = patientDao.findAll();
        return products.stream()
                .map(this::convertToDtoPatient)
                .collect(Collectors.toList());
    }
    public PatientEntity getPatientById(int idPatient) {
        return patientDao.findById(idPatient).orElse(null);
    }
    public PatientEntity updatePatient(int idPatient, PatientEntity pacientUpdate) {
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
        return patientDao.save(existingPatient);
    }

    public void deletePatient (int idPatient) {
        patientDao.deleteById(idPatient);
    }
    public List<PatientEntity> getAll() {
        return patientDao.findAll();
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
