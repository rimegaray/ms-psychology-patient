package com.kajucode.patient.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kajucode.patient.controller.dto.PatientCreationRequest;
import com.kajucode.patient.controller.dto.PatientResponse;
import com.kajucode.patient.controller.dto.PatientUpdateRequest;
import com.kajucode.patient.service.PatientService;
import com.kajucode.patient.service.dto.PatientDto;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

	 	@InjectMocks
	    private PatientController patientController;

	    @Mock
	    private PatientService patientServiceMock;


	    @Test
	    public void shouldGetPatientByIdReturnPatientResponseWhenServiceReturnPatientDto() {
	        // Precondiciones
	        int patientId = 1;
	        byte[] myFileTest = new byte[10];
	        Date date = new Date("12/01/24");
	        
	        // Configurar el mock del servicio para devolver un paciente simulado
	        PatientResponse expectedPatientResponse = PatientResponse.builder()
	                .fullName("Yeremi")
	                .dni(76351126)
	                .age(17)
	                .contactNumber(912923412)
	                .address("la casa del raton")
	                .email("yeremi.elraton@gmail.com")
	                .occupation("Estudiante")
	                .dateOfAdmission(date)
	                .lifeStory(myFileTest)
	                .observations("Esta locuaz")
	                .build();
	        
	        PatientDto patientDtoStub = PatientDto.builder()
	                .fullName("Yeremi")
	                .dni(76351126)
	                .age(17)
	                .contactNumber(912923412)
	                .address("la casa del raton")
	                .email("yeremi.elraton@gmail.com")
	                .occupation("Estudiante")
	                .dateOfAdmission(date)
	                .lifeStory(myFileTest)
	                .observations("Esta locuaz")
	                .build();

	        Mockito.when(patientServiceMock.getPatientById(anyInt())).thenReturn(patientDtoStub);

	        PatientResponse patientResponseResult = patientController.getPatientById(patientId);
	        
	        // Verificar el resultado
	        assertEquals(expectedPatientResponse.getFullName(), patientResponseResult.getFullName());
	        assertEquals(expectedPatientResponse.getDni(), patientResponseResult.getDni());
	        assertEquals(expectedPatientResponse.getAge(), patientResponseResult.getAge());
	        assertEquals(expectedPatientResponse.getContactNumber() , patientResponseResult.getContactNumber());
	        assertEquals(expectedPatientResponse.getAddress(), patientResponseResult.getAddress());
	        assertEquals(expectedPatientResponse.getEmail(), patientResponseResult.getEmail());
	        assertEquals(expectedPatientResponse.getOccupation(), patientResponseResult.getOccupation());
	        assertEquals(expectedPatientResponse.getDateOfAdmission(), patientResponseResult.getDateOfAdmission());
	        assertArrayEquals(expectedPatientResponse.getLifeStory(), patientResponseResult.getLifeStory());
	        assertEquals(expectedPatientResponse.getObservations(), patientResponseResult.getObservations());
	    }
	    
    @Test
    public void shouldPatientAddReturnPatientResponseWhenServiceReturnPatientDto() {
    	// Precondiciones
        byte[] myFileTest = new byte[10];
        Date date = new Date("12/01/24");
     
        PatientCreationRequest patientCreationRequest =PatientCreationRequest.builder()
        		.fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .occupation("Estudiante")
                .dateOfAdmission(date)
                .lifeStory(myFileTest)
                .observations("Esta locuaz")
                .build();
        
        PatientDto patientDtoStub = PatientDto.builder()
                .fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com") 
                .occupation("Estudiante")
                .dateOfAdmission(date)
                .lifeStory(myFileTest)
                .observations("Esta locuaz")
                .build();
        
        PatientResponse expectedPatientResponse = PatientResponse.builder()
                .fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .occupation("Estudiante")
                .dateOfAdmission(date)
                .lifeStory(myFileTest)
                .observations("Esta locuaz")
                .build();

                
        when(patientServiceMock.addPatient(any())).thenReturn(patientDtoStub);
 
        // Ejecución
        PatientResponse patientResponseResult = patientController.addPatient(patientCreationRequest);
 
        // Assert
        assertNotNull(patientResponseResult);
        assertEquals(expectedPatientResponse.getFullName(), patientResponseResult.getFullName());
        assertEquals(expectedPatientResponse.getDni(), patientResponseResult.getDni());
        assertEquals(expectedPatientResponse.getAge(), patientResponseResult.getAge());
        assertEquals(expectedPatientResponse.getContactNumber() , patientResponseResult.getContactNumber());
        assertEquals(expectedPatientResponse.getAddress(), patientResponseResult.getAddress());
        assertEquals(expectedPatientResponse.getEmail(), patientResponseResult.getEmail());
        assertEquals(expectedPatientResponse.getOccupation(), patientResponseResult.getOccupation());
        assertEquals(expectedPatientResponse.getDateOfAdmission(), patientResponseResult.getDateOfAdmission());
        assertEquals(expectedPatientResponse.getLifeStory(), patientResponseResult.getLifeStory());
        assertEquals(expectedPatientResponse.getObservations(), patientResponseResult.getObservations());
    } 
    
    @Test
    public void shouldPatientUpdateReturnPatientResponseWhenServiceReturnPatientDto() {
    	int id = 1;
    	byte[] myFileTest = new byte[10];
    	Date date = new Date("12/01/24");

    	PatientUpdateRequest expectedPatientResponse = new PatientUpdateRequest();
        expectedPatientResponse.setFullName("Yeremi");
        expectedPatientResponse.setDni(76351126);
        expectedPatientResponse.setAge(17);
        expectedPatientResponse.setContactNumber(912923412);
        expectedPatientResponse.setAddress("la casa del raton");
        expectedPatientResponse.setEmail("yeremi.elraton@gmail.com");
        expectedPatientResponse.setOccupation("Estudiante");
        expectedPatientResponse.setDateOfAdmission(date);
        expectedPatientResponse.setLifeStory(myFileTest);
        expectedPatientResponse.setObservations("Esta locuaz");
        
   
        
        PatientDto patientDtoStub = PatientDto.builder()
                .fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .occupation("Estudiante")
                .dateOfAdmission(date)
                .lifeStory(myFileTest)
                .observations("Esta locuaz")
                .build();
        
        when(patientServiceMock.updatePatient(anyInt(), any())).thenReturn(patientDtoStub);
        
        PatientResponse patientResponseResult = patientController.update(id, expectedPatientResponse);
        
        assertNotNull(patientResponseResult);
        assertEquals(expectedPatientResponse.getFullName(), patientResponseResult.getFullName());
        assertEquals(expectedPatientResponse.getDni(), patientResponseResult.getDni());
        assertEquals(expectedPatientResponse.getAge(), patientResponseResult.getAge());
        assertEquals(expectedPatientResponse.getContactNumber() , patientResponseResult.getContactNumber());
        assertEquals(expectedPatientResponse.getAddress(), patientResponseResult.getAddress());
        assertEquals(expectedPatientResponse.getEmail(), patientResponseResult.getEmail());
        assertEquals(expectedPatientResponse.getOccupation(), patientResponseResult.getOccupation());
        assertEquals(expectedPatientResponse.getDateOfAdmission(), patientResponseResult.getDateOfAdmission());
        assertEquals(expectedPatientResponse.getLifeStory(), patientResponseResult.getLifeStory());
        assertEquals(expectedPatientResponse.getObservations(), patientResponseResult.getObservations());
    }
    
    @Test
    public void shouldPatientDeleteIsSuccessfulWhenServiceIsSuccessful() {
    	int id = 1;
    	
    	doNothing().when(patientServiceMock).deletePatient(eq(id));
    	
        patientController.delete(id);
    	
        verify(patientServiceMock, times(1)).deletePatient(eq(id));

    }
    
    @Test
    public void shouldPatientGetAllReturnPatientResponseWhenServiceReturnListToPatientDto() {
    	byte[] myFileTest1 = new byte[10];
		byte[] myFileTest2 = new byte[10];
		Date date1 = new Date("12/01/24");
		Date date2 = new Date("13/01/24"); 
		
    	PatientDto expectedPatientResponse1 = PatientDto.builder()
                .fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .occupation("Estudiante")
                .dateOfAdmission(date1)
                .lifeStory(myFileTest1)
                .observations("Esta locuaz")
                .build();
		
    	PatientDto expectedPatientResponse2 = PatientDto.builder()
                .fullName("Juancho")
                .dni(87654321)
                .age(28)
                .contactNumber(987654321)
                .address("2 cm antes de las nubes")
                .email("juancho.elraton@gmail.com")
                .occupation("Come Cuates")
                .dateOfAdmission(date2)
                .lifeStory(myFileTest2)
                .observations("Esta lokazo")
                .build();
		
		List<PatientDto> samplePatients = Arrays.asList(expectedPatientResponse1, expectedPatientResponse2);
		
		when(patientServiceMock.getAll()).thenReturn(samplePatients);
		
		List<PatientResponse> patientResponseResult = patientController.getAll();
		
		assertEquals(patientResponseResult.get(0).getFullName(), samplePatients.get(0).getFullName());
		assertEquals(patientResponseResult.get(0).getDni(), samplePatients.get(0).getDni());
		assertEquals(patientResponseResult.get(0).getAge(), samplePatients.get(0).getAge());
		assertEquals(patientResponseResult.get(0).getContactNumber(), samplePatients.get(0).getContactNumber());
		assertEquals(patientResponseResult.get(0).getAddress(), samplePatients.get(0).getAddress());
		assertEquals(patientResponseResult.get(0).getEmail(), samplePatients.get(0).getEmail());
		assertEquals(patientResponseResult.get(0).getOccupation(), samplePatients.get(0).getOccupation());
		assertEquals(patientResponseResult.get(0).getDateOfAdmission(), samplePatients.get(0).getDateOfAdmission());
		assertEquals(patientResponseResult.get(0).getLifeStory(), samplePatients.get(0).getLifeStory());
		assertEquals(patientResponseResult.get(0).getObservations(), samplePatients.get(0).getObservations());
		
		assertEquals(patientResponseResult.get(1).getFullName(), samplePatients.get(1).getFullName());
		assertEquals(patientResponseResult.get(1).getDni(), samplePatients.get(1).getDni());
		assertEquals(patientResponseResult.get(1).getAge(), samplePatients.get(1).getAge());
		assertEquals(patientResponseResult.get(1).getContactNumber(), samplePatients.get(1).getContactNumber());
		assertEquals(patientResponseResult.get(1).getAddress(), samplePatients.get(1).getAddress());
		assertEquals(patientResponseResult.get(1).getEmail(), samplePatients.get(1).getEmail());
		assertEquals(patientResponseResult.get(1).getOccupation(), samplePatients.get(1).getOccupation());
		assertEquals(patientResponseResult.get(1).getDateOfAdmission(), samplePatients.get(1).getDateOfAdmission());
		assertEquals(patientResponseResult.get(1).getLifeStory(), samplePatients.get(1).getLifeStory());
		assertEquals(patientResponseResult.get(1).getObservations(), samplePatients.get(1).getObservations());
				
    }
}