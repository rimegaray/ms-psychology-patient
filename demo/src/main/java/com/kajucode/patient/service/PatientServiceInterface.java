package com.kajucode.patient.service;

import java.util.List;

import com.kajucode.patient.service.dto.PatientDto;

public interface PatientServiceInterface {
	PatientDto addPatient(PatientDto patientDto);
	List<PatientDto> getAll();
	PatientDto getPatientById(int patientId);
	PatientDto updatePatient(int patientId, PatientDto patientDto);
	void deletePatient (int idPatient);
}
