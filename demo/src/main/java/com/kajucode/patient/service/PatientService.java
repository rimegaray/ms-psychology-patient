package com.kajucode.patient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kajucode.patient.repository.dao.PatientDao;
import com.kajucode.patient.repository.entity.PsychologistEntity;
import com.kajucode.patient.service.convert.ServiceConverter;
import com.kajucode.patient.service.dto.MaplicacionDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PatientService implements PatientServiceInterface{

    private final PatientDao patientDao;

    public MaplicacionDto addPatient (MaplicacionDto maplicacionDto) {
        PsychologistEntity patientResult = patientDao.save(ServiceConverter.convertPatientDtoToEntityPatient(maplicacionDto));
        return ServiceConverter.convertPatientEntityToDtoPatient(patientResult);
    }
    public List<MaplicacionDto> getAll() {
        List<PsychologistEntity> psychologistEntities = patientDao.findAll();
        return psychologistEntities.stream()
                .map(ServiceConverter::convertPatientEntityToDtoPatient)
                .collect(Collectors.toList());
    }
    
    public MaplicacionDto getPatientById(int patientId) {
    	PsychologistEntity existingPatient = patientDao.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
		return ServiceConverter.convertPatientEntityToDtoPatient(existingPatient); 
    }
    
    public MaplicacionDto updatePatient(int patientId, MaplicacionDto maplicacionDto) {
    	PsychologistEntity existingPatient = patientDao.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
    	
        existingPatient.setFullName(maplicacionDto.getFullName());
        existingPatient.setDni(maplicacionDto.getDni());
        existingPatient.setAge(maplicacionDto.getAge()); 
        existingPatient.setContactNumber(maplicacionDto.getContactNumber());
        existingPatient.setAddress(maplicacionDto.getAddress());
        existingPatient.setEmail(maplicacionDto.getEmail());
        existingPatient.setOccupation(maplicacionDto.getOccupation());
        existingPatient.setDateOfAdmission(maplicacionDto.getDateOfAdmission());
        existingPatient.setLifeStory(maplicacionDto.getLifeStory());
        existingPatient.setObservations(maplicacionDto.getObservations()); 
     
        return ServiceConverter.convertPatientEntityToDtoPatient(patientDao.save(existingPatient));
    }

    public void deletePatient (int idPatient) {
        patientDao.deleteById(idPatient);
    }
}
