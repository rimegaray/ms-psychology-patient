package com.kajucode.patient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kajucode.patient.repository.dao.PatientDao;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.convert.ServiceConverter;
import com.kajucode.patient.service.dto.PatientDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PatientService implements PatientServiceInterface {

	private final PatientDao patientDao;

	@Override
	public Mono<PatientDto> addPatient(PatientDto patientDto) {
		return Mono.just(patientDto).map(dto -> ServiceConverter.convertPatientDtoToEntityPatient(dto))
				.flatMap(patientEntity -> Mono.just(patientDao.save(patientEntity)))
				.map(savedPatient -> ServiceConverter.convertPatientEntityToDtoPatient(savedPatient));
	}

	@Override
	public Flux<PatientDto> getAll() {
		return Flux.defer(() -> Flux.fromIterable(patientDao.findAll()))
				.map(ServiceConverter::convertPatientEntityToDtoPatient);
	}

	@Override
	public Mono<PatientDto> getPatientById(int patientId) {
		return Mono.fromSupplier(() -> {
			PatientEntity existingPatient = patientDao.findById(patientId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
			return ServiceConverter.convertPatientEntityToDtoPatient(existingPatient);
		});
	}

	@Override
	public Mono<PatientDto> updatePatient(int patientId, PatientDto patientDto) {
		return Mono.fromSupplier(() -> {
			PatientEntity existingPatient = patientDao.findById(patientId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

			existingPatient.setFullName(patientDto.getFullName());
			existingPatient.setDni(patientDto.getDni());
			existingPatient.setAge(patientDto.getAge());
			existingPatient.setContactNumber(patientDto.getContactNumber());
			existingPatient.setAddress(patientDto.getAddress());
			existingPatient.setEmail(patientDto.getEmail());
			existingPatient.setOccupation(patientDto.getOccupation());
			existingPatient.setDateOfAdmission(patientDto.getDateOfAdmission());
			existingPatient.setLifeStory(patientDto.getLifeStory());
			existingPatient.setObservations(patientDto.getObservations());

			PatientEntity updatedPatient = patientDao.save(existingPatient);
			return ServiceConverter.convertPatientEntityToDtoPatient(updatedPatient);
		});
	}

	@Override
    public Mono<Void> deletePatient(int idPatient) {
        return Mono.fromRunnable(() -> patientDao.deleteById(idPatient));
    }
}
