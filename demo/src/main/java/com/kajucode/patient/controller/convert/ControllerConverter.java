package com.kajucode.patient.controller.convert;

import com.kajucode.patient.controller.dto.PatientCreationRequest;
import com.kajucode.patient.controller.dto.PatientResponse;
import com.kajucode.patient.controller.dto.PatientUpdateRequest;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.dto.PatientDto;

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

}
