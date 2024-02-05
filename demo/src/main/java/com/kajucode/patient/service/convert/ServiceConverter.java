package com.kajucode.patient.service.convert;

import com.kajucode.patient.repository.entity.PsychologistEntity;
import com.kajucode.patient.service.dto.PatientDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConverter {
	public PatientDto convertPatientEntityToDtoPatient (PsychologistEntity psychologistEntity) {
        return PatientDto.builder().fullName(psychologistEntity.getFullName())
									.dni(psychologistEntity.getDni())
									.age(psychologistEntity.getAge())
									.contactNumber(psychologistEntity.getContactNumber())
									.address(psychologistEntity.getAddress())
									.email(psychologistEntity.getEmail())
									.occupation(psychologistEntity.getOccupation())
									.dateOfAdmission(psychologistEntity.getDateOfAdmission())
									.lifeStory(psychologistEntity.getLifeStory())
									.observations(psychologistEntity.getObservations())
									.build();
    }
    public PsychologistEntity convertPatientDtoToEntityPatient (PatientDto patientDto) {
        PsychologistEntity psychologistEntity = new PsychologistEntity();
        psychologistEntity.setFullName(patientDto.getFullName());
        psychologistEntity.setDni(patientDto.getDni());
        psychologistEntity.setAge(patientDto.getAge());
        psychologistEntity.setContactNumber(patientDto.getContactNumber());
        psychologistEntity.setAddress(patientDto.getAddress());
        psychologistEntity.setEmail(patientDto.getEmail());
        psychologistEntity.setOccupation(patientDto.getOccupation());
        psychologistEntity.setDateOfAdmission(patientDto.getDateOfAdmission());
        psychologistEntity.setLifeStory(patientDto.getLifeStory());
        psychologistEntity.setObservations(patientDto.getObservations());
        return psychologistEntity;
    }
}
