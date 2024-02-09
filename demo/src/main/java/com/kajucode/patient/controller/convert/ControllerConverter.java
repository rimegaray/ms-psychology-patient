package com.kajucode.patient.controller.convert;

import com.kajucode.patient.controller.dto.PatientResponse;
import com.kajucode.patient.controller.dto.PatientUpdateRequest;
import com.kajucode.patient.service.dto.MaplicacionDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerConverter {
	public MaplicacionDto convertPatientUpdatRequestToPatientDto (PatientUpdateRequest patientUpdateRequest) {
        return MaplicacionDto.builder().fullName(patientUpdateRequest.getFullName())
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
	
	public PatientResponse convertPatientDtoToPatientResponse (MaplicacionDto maplicacionDto) {
    	return PatientResponse.builder().fullName(maplicacionDto.getFullName())
										.dni(maplicacionDto.getDni())
										.age(maplicacionDto.getAge())
										.contactNumber(maplicacionDto.getContactNumber())
										.address(maplicacionDto.getAddress())
										.email(maplicacionDto.getEmail())
										.occupation(maplicacionDto.getOccupation())
										.dateOfAdmission(maplicacionDto.getDateOfAdmission())
										.lifeStory(maplicacionDto.getLifeStory())
										.observations(maplicacionDto.getObservations())
										.build();

    }

}
