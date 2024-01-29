package com.kajucode.patient.service.convert;

import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.dto.PatientDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConverter {
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
    public PatientEntity convertPatientDtoToEntityPatient (PatientDto patientDto) {
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
