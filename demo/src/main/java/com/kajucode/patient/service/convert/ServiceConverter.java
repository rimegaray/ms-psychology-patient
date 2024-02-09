package com.kajucode.patient.service.convert;

import com.kajucode.patient.repository.entity.PsychologistEntity;
import com.kajucode.patient.service.dto.MaplicacionDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConverter {
	public MaplicacionDto convertPatientEntityToDtoPatient (PsychologistEntity psychologistEntity) {
        return MaplicacionDto.builder().fullName(psychologistEntity.getFullName())
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
    public PsychologistEntity convertPatientDtoToEntityPatient (MaplicacionDto maplicacionDto) {
        PsychologistEntity psychologistEntity = new PsychologistEntity();
        psychologistEntity.setFullName(maplicacionDto.getFullName());
        psychologistEntity.setDni(maplicacionDto.getDni());
        psychologistEntity.setAge(maplicacionDto.getAge());
        psychologistEntity.setContactNumber(maplicacionDto.getContactNumber());
        psychologistEntity.setAddress(maplicacionDto.getAddress());
        psychologistEntity.setEmail(maplicacionDto.getEmail());
        psychologistEntity.setOccupation(maplicacionDto.getOccupation());
        psychologistEntity.setDateOfAdmission(maplicacionDto.getDateOfAdmission());
        psychologistEntity.setLifeStory(maplicacionDto.getLifeStory());
        psychologistEntity.setObservations(maplicacionDto.getObservations());
        return psychologistEntity;
    }
}
