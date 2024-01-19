package com.kajucode.patient.service.excepcion;

public class PatientNotFoundException extends RuntimeException {

	public PatientNotFoundException(String message) {
        super(message);
    }
}
