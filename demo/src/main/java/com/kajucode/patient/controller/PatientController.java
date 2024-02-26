package com.kajucode.patient.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
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
import com.kajucode.patient.service.PatientServiceInterface;
import com.kajucode.patient.service.dto.PatientDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientServiceInterface patientServiceInterface;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PatientResponse> addPatient (@RequestBody PatientCreationRequest patientRequest) {
        PatientDto newPatient = PatientDto.builder()
                .fullName(patientRequest.getFullName())
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

        return patientServiceInterface.addPatient(newPatient)
                .map(ControllerConverter::convertPatientDtoToPatientResponse);
    }

    @GetMapping("/{id}")
    public Mono<PatientResponse> getPatientById(@PathVariable int id) {
        return patientServiceInterface.getPatientById(id)
                .map(ControllerConverter::convertPatientDtoToPatientResponse);
    }

    @PutMapping("/{id}")
    public Mono<PatientResponse> update(@PathVariable int id, @RequestBody PatientUpdateRequest patientUpdateRequest) {
        PatientDto patientDto = ControllerConverter.convertPatientUpdatRequestToPatientDto(patientUpdateRequest);
        return patientServiceInterface.updatePatient(id, patientDto)
                .map(ControllerConverter::convertPatientDtoToPatientResponse);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable int id) {
        return patientServiceInterface.deletePatient(id);
    }

    
    @GetMapping
    public Flux<PatientResponse> getAll() {
        return patientServiceInterface.getAll()
                .map(ControllerConverter::convertPatientDtoToPatientResponse);
    }

    
     

}
