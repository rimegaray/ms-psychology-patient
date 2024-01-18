package com.example.demo.controller;

import com.example.demo.controller.convert.ControllerConverter;
import com.example.demo.controller.dto.PatientCreationRequest;
import com.example.demo.controller.dto.PatientListRequest;
import com.example.demo.controller.dto.PatientResponse;
import com.example.demo.controller.dto.PatientUpdateRequest;
import com.example.demo.repository.dto.PatientDto;
import com.example.demo.repository.entity.PatientEntity;
import com.example.demo.service.PatientService;

import lombok.RequiredArgsConstructor;

import java.util.List;
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
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto addPatient (@RequestBody PatientCreationRequest patientRequest) {
    	
    	return PatientDto.builder().fullName(patientRequest.getFullName())
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
    }

    @GetMapping("/{id}")
    public PatientEntity getById(@PathVariable int id) {
        return ControllerConverter.convertToEntityPatient(patientService.getPatientById(id));
    }
    @PutMapping("/{id}")
    public PatientResponse update(@PathVariable int id, @RequestBody PatientUpdateRequest patientUpdateRequest) {
    	PatientDto patientDto = ControllerConverter.convertPatientUpdatRequestToPatientDto(patientUpdateRequest);
    	return ControllerConverter.convertPatientDtoToPatientResponse(patientService.updatePatient(id, patientDto));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        patientService.deletePatient(id);
    }
    @GetMapping
    public List<PatientResponse> getAll() {
        List<PatientDto> patients = patientService.getAll();
        return patients.stream()
                .map(ControllerConverter::convertPatientDtoToPatientResponse)
                .collect(Collectors.toList());
    }
    
    

}
