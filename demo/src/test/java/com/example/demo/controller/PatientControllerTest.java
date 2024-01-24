package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kajucode.patient.controller.PatientController;
import com.kajucode.patient.repository.dao.PatientDao;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.PatientService;
import com.kajucode.patient.service.dto.PatientDto;
import com.kajucode.patient.controller.dto.PatientCreationRequest;
import com.kajucode.patient.controller.convert.ControllerConverter;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

	 @InjectMocks
	    private PatientController patientController;

	    @Mock
	    private PatientService patientService;

	    @Mock
	    private ControllerConverter controllerConverter; // Supongamos que tienes un convertidor


    @Test
    public void testGetPatientById() {
        // Datos de prueba
        int patientId = 1;
        PatientEntity mockPatientEntity = new PatientEntity();
        mockPatientEntity.setPatientId(patientId);
        mockPatientEntity.setFullName("John Doe");

        // Simular el comportamiento del servicio
        when(patientService.getPatientById(patientId)).thenReturn(Optional.of(mockPatientEntity));

        // Llamar al método del controlador que se está probando
        ResponseEntity<PatientDto> responseEntity = patientController.getPatientById(patientId);
        // Verificar el resultado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("John Doe", responseEntity.getBody().getFullName());
    }

    @Test
    public void testAddPatient() {
    	//Data of proof
    	byte[] myFileTest = new byte[10];
		
    	PatientCreationRequest mockPatientCreationRequest = new PatientCreationRequest();
		mockPatientCreationRequest.setFullName("Yeremi");
		mockPatientCreationRequest.setDni(76351126);
		mockPatientCreationRequest.setAge(17);
		mockPatientCreationRequest.setContactNumber(912923412);
		mockPatientCreationRequest.setAddress("la casa del raton");
		mockPatientCreationRequest.setEmail("yeremi.elraton@gmail.com");
		mockPatientCreationRequest.setOccupation("Estudiante");
		mockPatientCreationRequest.setDateOfAdmission(new Date("12/01/24"));
		mockPatientCreationRequest.setLifeStory(myFileTest);
		mockPatientCreationRequest.setObservations("Esta locuaz");
		
		 // Simular el comportamiento del convertidor
        PatientDto mockPatientDto = new PatientDto();
        when(controllerConverter.convertPatientCreationRequestToPatientDto(mockPatientCreationRequest)).thenReturn(mockPatientDto);

        // Simular el comportamiento del servicio
        when(patientService.addPatient(mockPatientDto)).thenReturn(mockPatientDto);

        // Llamar al método del controlador que se está probando
        PatientCreationRequest addedPatient = patientController.addPatient(mockPatientCreationRequest);

        // Verificar el resultado
        assertEquals("Yeremi", addedPatient.getFullName());
    }
}
