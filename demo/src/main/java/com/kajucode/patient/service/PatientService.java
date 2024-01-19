package com.kajucode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.kajucode.patient.repository.dao.PatientDao;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.convert.ServiceConverter;
import com.kajucode.patient.service.dto.PatientDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientDao patientDao;

    public PatientDto addPatient (PatientDto patientDto) {
        PatientEntity patientResult = patientDao.save(ServiceConverter.convertToEntityPatient(patientDto));
        return ServiceConverter.convertPatientEntityToDtoPatient(patientResult);
    }
    public List<PatientDto> getAll() {
        List<PatientEntity> patientEntities = patientDao.findAll();
        return patientEntities.stream()
                .map(ServiceConverter::convertPatientEntityToDtoPatient)
                .collect(Collectors.toList());
    }
    public PatientDto getPatientById(int patientId) {
        PatientEntity patientEntity = patientDao.findById(patientId)
                .orElseThrow();
        return ServiceConverter.convertPatientEntityToDtoPatient(patientEntity);
    }
    
    public PatientDto updatePatient(int patientId, PatientDto patientDto) {
        PatientEntity existingPatient = patientDao.findById(patientId).orElseThrow(null);
        if (existingPatient != null) {
            existingPatient.setFullName(patientDto.getFullName());
            existingPatient.setDni(patientDto.getDni());
            existingPatient.setAge(patientDto.getAge());
            existingPatient.setContactNumber(patientDto.getContactNumber());
            existingPatient.setAddress(patientDto.getAddress());
            existingPatient.setEmail(patientDto.getEmail());
            existingPatient.setOccupation(patientDto.getOccupation());
            existingPatient.setDateOfAdmission(patientDto.getDateOfAdmission());
            existingPatient.setLifeStory(patientDto.getLifeStory());
            existingPatient.setObservations(patientDto.getObservations());
        }
        return ServiceConverter.convertPatientEntityToDtoPatient(patientDao.save(existingPatient));
    }

    public void deletePatient (int idPatient) {
        patientDao.deleteById(idPatient);
    }
}
