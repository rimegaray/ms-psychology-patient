package com.kajucode.patient.service;

import com.kajucode.patient.service.dto.PatientDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientServiceInterface {
	Mono<PatientDto> addPatient(PatientDto patientDto);
	Flux<PatientDto> getAll();
	Mono<PatientDto> getPatientById(int patientId);
	Mono<PatientDto> updatePatient(int patientId, PatientDto patientDto);
	Mono<Void> deletePatient (int idPatient);
}
