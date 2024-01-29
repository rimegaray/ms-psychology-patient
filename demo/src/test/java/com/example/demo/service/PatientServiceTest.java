package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.kajucode.patient.repository.dao.PatientDao;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.PatientService;
import com.kajucode.patient.service.convert.ServiceConverter;
import com.kajucode.patient.service.dto.PatientDto;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

	private static PatientService patientService;

	@Mock
	PatientDao patientDaoMock;

	@BeforeEach
	public void beforeAll() {
		patientService = new PatientService(patientDaoMock);
	}

	@Test
	public void addPatientShouldReturnSuccessfulWhenDaoIsOk() {
		// Precondiciones
		byte[] myFileTest = new byte[10];

		PatientDto patientDtoExpected = PatientDto.builder().fullName(("Yeremi"))
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
		
		PatientDto patientDto = PatientDto.builder().fullName(("Yeremi"))
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
  
		PatientEntity patientEntityResult = new PatientEntity();
		patientEntityResult.setPatientId(0);
		patientEntityResult.setFullName("Yeremi");
		patientEntityResult.setDni(76351126);
		patientEntityResult.setAge(17);
		patientEntityResult.setContactNumber(912923412);
		patientEntityResult.setAddress("la casa del raton");
		patientEntityResult.setEmail("yeremi.elraton@gmail.com");
		patientEntityResult.setOccupation("Estudiante");
		patientEntityResult.setDateOfAdmission(new Date("12/01/24"));
		patientEntityResult.setLifeStory(myFileTest);
		patientEntityResult.setObservations("Esta locuaz");
		

		Mockito.when(patientDaoMock.save(Mockito.any())).thenReturn(patientEntityResult);
		// Ejecucion
		PatientDto patientDtoResult = patientService.addPatient(patientDto);

		// Asserts
		assertNotNull(patientDtoResult);
		assertEquals(patientDtoResult.getFullName(), patientDtoExpected.getFullName());
		assertEquals(patientDtoResult.getFullName(), patientDtoExpected.getFullName());
		assertEquals(patientDtoResult.getDni(), patientDtoExpected.getDni());
		assertEquals(patientDtoResult.getAge(), patientDtoExpected.getAge());
		assertEquals(patientDtoResult.getContactNumber(), patientDtoExpected.getContactNumber());
		assertEquals(patientDtoResult.getAddress(), patientDtoExpected.getAddress());
		assertEquals(patientDtoResult.getEmail(), patientDtoExpected.getEmail());
		assertEquals(patientDtoResult.getOccupation(), patientDtoExpected.getOccupation());
		assertEquals(patientDtoResult.getDateOfAdmission(), patientDtoExpected.getDateOfAdmission());
		assertEquals(patientDtoResult.getLifeStory(), patientDtoExpected.getLifeStory());
		assertEquals(patientDtoResult.getObservations(), patientDtoExpected.getObservations());
	}

	@Test
	public void testDeletePatient() {
		
		//precondiciones
		Mockito.doNothing().when(patientDaoMock).deleteById(Mockito.anyInt());
		//ejecucion real
		patientService.deletePatient(123);
		//verificaciones
		Mockito.verify(patientDaoMock, Mockito.times(1)).deleteById(123);
	}

	@Test
	public void testGetAll() {
		byte[] myFileTest1 = new byte[10];
		byte[] myFileTest2 = new byte[10];
		Date date = new Date("12/01/24");
		
		
		PatientEntity patientEntity1 = new PatientEntity();
		patientEntity1.setPatientId(0);
		patientEntity1.setFullName("Yeremi");
		patientEntity1.setDni(76351126);
		patientEntity1.setAge(17);
		patientEntity1.setContactNumber(912923412);
		patientEntity1.setAddress("la casa del raton");
		patientEntity1.setEmail("yeremi.elraton@gmail.com");
		patientEntity1.setOccupation("Estudiante");
		patientEntity1.setDateOfAdmission(date);
		patientEntity1.setLifeStory(myFileTest1);
		patientEntity1.setObservations("Esta locuaz");
		
		PatientEntity patientEntity2 = new PatientEntity();
		patientEntity2.setPatientId(0);
		patientEntity2.setFullName("Juancho");
		patientEntity2.setDni(87654321);
		patientEntity2.setAge(28);
		patientEntity2.setContactNumber(987654321);
		patientEntity2.setAddress("2 cm antes de las nubes");
		patientEntity2.setEmail("juancho.elraton@gmail.com");
		patientEntity2.setOccupation("Come Cuates");
		patientEntity2.setDateOfAdmission(date);
		patientEntity2.setLifeStory(myFileTest2);
		patientEntity2.setObservations("Esta lokazo");
		
		List<PatientEntity> samplePatients = Arrays.asList(patientEntity1, patientEntity2);

		Mockito.when(patientDaoMock.findAll()).thenReturn(samplePatients);

		List<PatientDto> result = patientService.getAll();

		// verify
		assertNotNull(result);
		assertEquals(samplePatients.size(), result.size());
		verify(patientDaoMock, Mockito.times(1)).findAll();
		
		assertEquals(result.get(0).getFullName(), samplePatients.get(0).getFullName());
		assertEquals(result.get(0).getDni(), samplePatients.get(0).getDni());
		assertEquals(result.get(0).getAge(), samplePatients.get(0).getAge());
		assertEquals(result.get(0).getContactNumber(), samplePatients.get(0).getContactNumber());
		assertEquals(result.get(0).getAddress(), samplePatients.get(0).getAddress());
		assertEquals(result.get(0).getEmail(), samplePatients.get(0).getEmail());
		assertEquals(result.get(0).getOccupation(), samplePatients.get(0).getOccupation());
		assertEquals(result.get(0).getDateOfAdmission(), samplePatients.get(0).getDateOfAdmission());
		assertEquals(result.get(0).getLifeStory(), samplePatients.get(0).getLifeStory());
		assertEquals(result.get(0).getObservations(), samplePatients.get(0).getObservations());
		
		assertEquals(result.get(1).getFullName(), samplePatients.get(1).getFullName());
		assertEquals(result.get(1).getDni(), samplePatients.get(1).getDni());
		assertEquals(result.get(1).getAge(), samplePatients.get(1).getAge());
		assertEquals(result.get(1).getContactNumber(), samplePatients.get(1).getContactNumber());
		assertEquals(result.get(1).getAddress(), samplePatients.get(1).getAddress());
		assertEquals(result.get(1).getEmail(), samplePatients.get(1).getEmail());
		assertEquals(result.get(1).getOccupation(), samplePatients.get(1).getOccupation());
		assertEquals(result.get(1).getDateOfAdmission(), samplePatients.get(1).getDateOfAdmission());
		assertEquals(result.get(1).getLifeStory(), samplePatients.get(1).getLifeStory());
		assertEquals(result.get(1).getObservations(), samplePatients.get(1).getObservations());
	} 
	@Test
	public void testGetById() {
		int patientId = 1;
        byte[] myFileTest = new byte[10];
        Date date = new Date("12/01/24");
        
        PatientDto patientDtoResponse = PatientDto.builder()
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
        
        PatientEntity patientEntityStub = new PatientEntity();
		patientEntityStub.setPatientId(0);
		patientEntityStub.setFullName("Yeremi");
		patientEntityStub.setDni(76351126);
		patientEntityStub.setAge(17);
		patientEntityStub.setContactNumber(912923412);
		patientEntityStub.setAddress("la casa del raton");
		patientEntityStub.setEmail("yeremi.elraton@gmail.com");
		patientEntityStub.setOccupation("Estudiante");
		patientEntityStub.setDateOfAdmission(date);
		patientEntityStub.setLifeStory(myFileTest);
		patientEntityStub.setObservations("Esta locuaz");
		
		when(patientDaoMock.findById(anyInt())).thenReturn(Optional.of(patientEntityStub));
		
		PatientDto result = patientService.getPatientById(patientId);
		
		assertEquals(patientDtoResponse.getFullName(), result.getFullName());
        assertEquals(patientDtoResponse.getDni(), result.getDni());
        assertEquals(patientDtoResponse.getAge(), result.getAge());
        assertEquals(patientDtoResponse.getContactNumber() , result.getContactNumber());
        assertEquals(patientDtoResponse.getAddress(), result.getAddress());
        assertEquals(patientDtoResponse.getEmail(), result.getEmail());
        assertEquals(patientDtoResponse.getOccupation(), result.getOccupation());
        assertEquals(patientDtoResponse.getDateOfAdmission(), result.getDateOfAdmission());
        assertArrayEquals(patientDtoResponse.getLifeStory(), result.getLifeStory());
        assertEquals(patientDtoResponse.getObservations(), result.getObservations());     
	}
	@Test
	public void testUpdatePatient() {
		int id = 1;
    	byte[] myFileTest = new byte[10];
    	Date date = new Date("12/01/24");
    	
    	PatientDto patientDtoResponse = PatientDto.builder()
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
        
        PatientEntity patientEntityStub = new PatientEntity();
		patientEntityStub.setPatientId(0);
		patientEntityStub.setFullName("Yeremi");
		patientEntityStub.setDni(76351126);
		patientEntityStub.setAge(17);
		patientEntityStub.setContactNumber(912923412);
		patientEntityStub.setAddress("la casa del raton");
		patientEntityStub.setEmail("yeremi.elraton@gmail.com");
		patientEntityStub.setOccupation("Estudiante");
		patientEntityStub.setDateOfAdmission(date);
		patientEntityStub.setLifeStory(myFileTest);
		patientEntityStub.setObservations("Esta locuaz");
		
		when(patientDaoMock.save(any())).thenReturn(patientEntityStub);
		when(patientDaoMock.findById(anyInt())).thenReturn(Optional.of(patientEntityStub)); 
		
		PatientDto result = patientService.updatePatient(id, patientDtoResponse);
		
		patientService.deletePatient(id);

        verify(patientDaoMock, times(1)).findById(id);
		
		assertEquals(patientDtoResponse.getFullName(), result.getFullName());
        assertEquals(patientDtoResponse.getDni(), result.getDni());
        assertEquals(patientDtoResponse.getAge(), result.getAge());
        assertEquals(patientDtoResponse.getContactNumber() , result.getContactNumber());
        assertEquals(patientDtoResponse.getAddress(), result.getAddress());
        assertEquals(patientDtoResponse.getEmail(), result.getEmail());
        assertEquals(patientDtoResponse.getOccupation(), result.getOccupation());
        assertEquals(patientDtoResponse.getDateOfAdmission(), result.getDateOfAdmission());
        assertArrayEquals(patientDtoResponse.getLifeStory(), result.getLifeStory());
        assertEquals(patientDtoResponse.getObservations(), result.getObservations()); 
		
		
	}
	@Test
    public void testGetPatientById_PatientNotFound() {
		
        int id = 1;
        when(patientDaoMock.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            patientService.getPatientById(id);
        });
	}
}