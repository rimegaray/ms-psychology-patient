package com.kajucode.patient.service;

import java.util.List;

import com.kajucode.patient.service.dto.MaplicacionDto;

public interface PatientServiceInterface {
	MaplicacionDto addPatient(MaplicacionDto maplicacionDto);
	List<MaplicacionDto> getAll();
	MaplicacionDto getPatientById(int patientId);
	MaplicacionDto updatePatient(int patientId, MaplicacionDto maplicacionDto);
	void deletePatient (int idPatient);
}
