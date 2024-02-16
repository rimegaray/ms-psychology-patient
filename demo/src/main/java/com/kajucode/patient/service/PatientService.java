package com.kajucode.patient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kajucode.patient.repository.dao.PatientDao;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.convert.ServiceConverter;
import com.kajucode.patient.service.dto.PatientDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PatientService implements PatientServiceInterface{

    private final PatientDao patientDao;

    public PatientDto addPatient (PatientDto patientDto) {
        PatientEntity patientResult = patientDao.save(ServiceConverter.convertPatientDtoToEntityPatient(patientDto));
        return ServiceConverter.convertPatientEntityToDtoPatient(patientResult);
    }
    public List<PatientDto> getAll() {
        List<PatientEntity> patientEntities = patientDao.findAll();
        return patientEntities.stream()
                .map(ServiceConverter::convertPatientEntityToDtoPatient)
                .collect(Collectors.toList());
    }
    
    public PatientDto getPatientById(int patientId) {
    	PatientEntity existingPatient = patientDao.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
		return ServiceConverter.convertPatientEntityToDtoPatient(existingPatient); 
    }
    
    public PatientDto updatePatient(int patientId, PatientDto patientDto) {
    	PatientEntity existingPatient = patientDao.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
    	
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
     
        return ServiceConverter.convertPatientEntityToDtoPatient(patientDao.save(existingPatient));
    }

    public void deletePatient (int idPatient) {
        patientDao.deleteById(idPatient);
    }
}
