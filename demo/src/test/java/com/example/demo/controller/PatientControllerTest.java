package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kajucode.patient.controller.PatientController;
import com.kajucode.patient.controller.convert.ControllerConverter;
import com.kajucode.patient.controller.dto.PatientCreationRequest;
import com.kajucode.patient.controller.dto.PatientResponse;
import com.kajucode.patient.controller.dto.PatientUpdateRequest;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.PatientService;
import com.kajucode.patient.service.dto.PatientDto;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

	 	//@InjectMocks
	    //private PatientController patientController;

	    @Mock
	    private PatientService patientServiceMock;

	    @Mock
	    private ControllerConverter controllerConverter; // Supongamos que tienes un convertidor
	    
	    PatientController patientController = new PatientController(patientServiceMock);


	    @Test
	    public void testGetPatientById() {
	        // Precondiciones
	        int patientId = 1;
	        byte[] myFileTest = new byte[10];
	        
	        // Configurar el mock del servicio para devolver un paciente simulado
	        PatientEntity mockPatientEntity = new PatientEntity();
	        mockPatientEntity.setPatientId(patientId);
	        mockPatientEntity.setFullName("Yeremi");
	        mockPatientEntity.setDni(76351126);
	        mockPatientEntity.setAge(17);
	        mockPatientEntity.setContactNumber(912923412);
	        mockPatientEntity.setAddress("la casa del raton");
	        mockPatientEntity.setEmail("yeremi.elraton@gmail.com");
	        mockPatientEntity.setOccupation("Estudiante");
	        mockPatientEntity.setDateOfAdmission(new Date("12/01/24"));
	        mockPatientEntity.setLifeStory(myFileTest);
	        mockPatientEntity.setObservations("Esta locuaz");
	        
	        Mockito.when(patientServiceMock.getPatientById(patientId)).thenReturn(Optional.of(mockPatientEntity));

	        // Llamar al método del controlador que se está probando
	        ResponseEntity<PatientDto> responseEntity = patientController.getPatientById(patientId);
	        
	        // Verificar el resultado
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals("Yeremi", responseEntity.getBody().getFullName());
	        assertEquals(76351126, responseEntity.getBody().getDni());
	        assertEquals(17, responseEntity.getBody().getAge());
	        assertEquals(912923412 , responseEntity.getBody().getContactNumber());
	        assertEquals("la casa del raton", responseEntity.getBody().getAddress());
	        assertEquals("yeremi.elraton@gmail.com", responseEntity.getBody().getEmail());
	        assertEquals("Estudiante", responseEntity.getBody().getOccupation());
	        assertEquals(new Date("12/01/24"), responseEntity.getBody().getDateOfAdmission());
	        assertArrayEquals(myFileTest, responseEntity.getBody().getLifeStory());
	        assertEquals("Esta locuaz", responseEntity.getBody().getObservations());
	    }
    @Test
    public void testAddPatient() {
    	// Precondiciones
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

        PatientDto expectedPatientDto = PatientDto.builder()
                .fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .occupation("Estudiante")
                .dateOfAdmission(new Date("12/01/24"))
                .lifeStory(myFileTest)
                .observations("Esta locuaz")
                .build();
 
        // Ejecución
        PatientDto addedPatientDto = patientController.addPatient(mockPatientCreationRequest);
 
        // Assert
        assertNotNull(addedPatientDto);
        assertEquals("Yeremi", addedPatientDto.getFullName());
        assertEquals(76351126, addedPatientDto.getDni());
        assertEquals(17, addedPatientDto.getAge());
        assertEquals(912923412 , addedPatientDto.getContactNumber());
        assertEquals("la casa del raton", addedPatientDto.getAddress());
        assertEquals("yeremi.elraton@gmail.com", addedPatientDto.getEmail());
        assertEquals("Estudiante", addedPatientDto.getOccupation());
        assertEquals(new Date("12/01/24"), addedPatientDto.getDateOfAdmission());
        assertEquals(myFileTest, addedPatientDto.getLifeStory());
        assertEquals("Esta locuaz", addedPatientDto.getObservations());
    } 
    @Test
    public void testUpdatePatient() {
    	int id = 1;
    	byte[] myFileTest = new byte[10];

    	PatientUpdateRequest patientUpdateRequest = new PatientUpdateRequest();
        patientUpdateRequest.setFullName("Yeremi");
        patientUpdateRequest.setDni(76351126);
        patientUpdateRequest.setAge(17);
        patientUpdateRequest.setContactNumber(912923412);
        patientUpdateRequest.setAddress("la casa del raton");
        patientUpdateRequest.setEmail("yeremi.elraton@gmail.com");
        patientUpdateRequest.setOccupation("Estudiante");
        patientUpdateRequest.setDateOfAdmission(new Date("12/01/24"));
        patientUpdateRequest.setLifeStory(myFileTest);
        patientUpdateRequest.setObservations("Esta locuaz");
        
        patientController.update(id, patientUpdateRequest);
        
        verify(patientController).update(eq(id), eq(patientUpdateRequest));
        
        
    }
}