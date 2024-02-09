package com.kajucode.patient.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kajucode.patient.controller.convert.ControllerConverter;
import com.kajucode.patient.controller.dto.PatientCreationRequest;
import com.kajucode.patient.controller.dto.PatientResponse;
import com.kajucode.patient.controller.dto.PatientUpdateRequest;
import com.kajucode.patient.repository.entity.PsychologistEntity;
import com.kajucode.patient.service.PatientService;
import com.kajucode.patient.service.PatientServiceInterface;
import com.kajucode.patient.service.convert.ServiceConverter;
import com.kajucode.patient.service.dto.MaplicacionDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientServiceInterface patientServiceInterface;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponse addPatient (@RequestBody PatientCreationRequest patientRequest) {
    	MaplicacionDto newPatient = MaplicacionDto.builder().fullName(patientRequest.getFullName())
													.dni(patientRequest.getDni())
													.age(patientRequest.getAge())
													.contactNumber(patientRequest.getContactNumber())
													.address(patientRequest.getAddress())
													.email(patientRequest.getEmail())
													.occupation(patientRequest.getOccupation())
													.dateOfAdmission(patientRequest.getDateOfAdmission())
													.lifeStory(patientRequest.getLifeStory())
													.observations(patientRequest.getObservations())
													.build();
    	return ControllerConverter.convertPatientDtoToPatientResponse(patientServiceInterface.addPatient(newPatient));
    } 
    @GetMapping("/{id}")
    public PatientResponse getPatientById(@PathVariable int id) {
        return ControllerConverter.convertPatientDtoToPatientResponse(patientServiceInterface.getPatientById(id));
    }
 
    @PutMapping("/{id}")
    public PatientResponse update(@PathVariable int id, @RequestBody PatientUpdateRequest patientUpdateRequest) {
    	MaplicacionDto maplicacionDto = ControllerConverter.convertPatientUpdatRequestToPatientDto(patientUpdateRequest);
    	return ControllerConverter.convertPatientDtoToPatientResponse(patientServiceInterface.updatePatient(id, maplicacionDto));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        patientServiceInterface.deletePatient(id);
    }
    @GetMapping
    public List<PatientResponse> getAll() {
        List<MaplicacionDto> patients = patientServiceInterface.getAll();
        return patients.stream()
                .map(ControllerConverter::convertPatientDtoToPatientResponse)
                .collect(Collectors.toList());
    } 
    
     

}
