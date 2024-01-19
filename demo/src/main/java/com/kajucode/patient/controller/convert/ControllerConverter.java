package com.kajucode.patient.controller.convert;

import com.kajucode.patient.controller.dto.PatientResponse;
import com.kajucode.patient.controller.dto.PatientUpdateRequest;
import com.kajucode.patient.repository.dto.PatientDto;
import com.kajucode.patient.repository.entity.PatientEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerConverter {
	public PatientDto convertPatientUpdatRequestToPatientDto (PatientUpdateRequest patientUpdateRequest) {
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
	public PatientResponse convertPatientDtoToPatientResponse (PatientDto patientDto) {
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
	public PatientResponse convertPatientEntityToPatientResponse (PatientEntity patientEntity) {
    	return PatientResponse.builder().fullName(patientEntity.getFullName())
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
    	public PatientDto convertPatientEntityToDtoPatient (PatientEntity patientEntity) {
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
	public PatientEntity convertToEntityPatient (PatientDto patientDto) {
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
