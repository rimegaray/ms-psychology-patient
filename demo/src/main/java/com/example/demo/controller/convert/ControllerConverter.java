package com.example.demo.controller.convert;

import com.example.demo.controller.dto.PatientResponse;
import com.example.demo.controller.dto.PatientUpdateRequest;
import com.example.demo.repository.dto.PatientDto;
import com.example.demo.repository.entity.PatientEntity;

public class ControllerConverter {
	public static PatientDto convertPatientUpdatRequestToPatientDto (PatientUpdateRequest patientUpdateRequest) {
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
	public static PatientResponse convertPatientDtoToPatientResponse (PatientDto patientDto) {
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
	public static PatientEntity convertToEntityPatient (PatientDto patientDto) {
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
