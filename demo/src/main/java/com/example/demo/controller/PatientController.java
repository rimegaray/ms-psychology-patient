package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
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
        return patientService.getPatientById(id);
    }
    @PutMapping("/{id}")
    public PatientResponse update(@PathVariable int id, @RequestBody PatientUpdateRequest patientUpdateRequest) {
    	PatientDto patientDto = convertPatientUpdatRequestToPatientDto(patientUpdateRequest);
    	return convertPatientDtoToPatientResponse(patientService.updatePatient(id, patientDto));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        patientService.deletePatient(id);
    }
    @GetMapping
    public List<PatientResponse> getAll() {
        List<PatientDto> patients = patientService.getAll();
        return patients.stream()
                .map(this::convertPatientDtoToPatientResponse)
                .collect(Collectors.toList());
    }
    
    private PatientDto convertPatientUpdatRequestToPatientDto (PatientUpdateRequest patientUpdateRequest) {
        return PatientDto.builder().fullName(patientUpdateRequest.getFullName())
				        			.dni(patientUpdateRequest.getDni())
				        			.age(patientUpdateRequest.getAge())
				        			.contactNumber(patientUpdateRequest.getContactNumber())
				        			.address(patientUpdateRequest.getAddress())
				        			.email(patientUpdateRequest.getEmail())
				        			.occupation(patientUpdateRequest.getOccupation())
				        			.dateOfAdmission(patientUpdateRequest.getDateOfAdmission())
				        			.lifeStory(patientUpdateRequest.getLifeStory())
				        			.observations(patientUpdateRequest.getObservations())
				        			.build();
    }
    private PatientResponse convertPatientDtoToPatientResponse (PatientDto patientDto) {
    	return PatientResponse.builder().fullName(patientDto.getFullName())
										.dni(patientDto.getDni())
										.age(patientDto.getAge())
										.contactNumber(patientDto.getContactNumber())
										.address(patientDto.getAddress())
										.email(patientDto.getEmail())
										.occupation(patientDto.getOccupation())
										.dateOfAdmission(patientDto.getDateOfAdmission())
										.lifeStory(patientDto.getLifeStory())
										.observations(patientDto.getObservations())
										.build();

    }

}
