package com.kajucode.patient.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.kajucode.patient.repository.dao.PatientDao;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.dto.PatientDto;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

	@Mock
	PatientDao patientDaoMock;

	@InjectMocks
    private PatientService patientService;

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
	public void shouldPatientDeleteIsSuccessfulWhenServiceIsSuccessful() {
		
		//precondiciones
		Mockito.doNothing().when(patientDaoMock).deleteById(Mockito.anyInt());
		//ejecucion real
		patientService.deletePatient(123);
		//verificaciones
		Mockito.verify(patientDaoMock, Mockito.times(1)).deleteById(123);
	}

	@Test
	public void shouldGetAllReturnSuccessfulWhenDaoIsOk() {
		byte[] myFileTest1 = new byte[10];
		byte[] myFileTest2 = new byte[10];
		Date date1 = new Date("12/01/24");
		Date date2= new Date("12/02/24");
		
		PatientDto patientDtoExpected1 = PatientDto.builder().fullName(("Yeremi"))
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
		
		PatientDto patientDtoExpected2 = PatientDto.builder().fullName(("Juancho"))
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
		
		
		PatientEntity stubPatients1 = new PatientEntity();
		stubPatients1.setPatientId(0);
		stubPatients1.setFullName("Yeremi");
		stubPatients1.setDni(76351126);
		stubPatients1.setAge(17);
		stubPatients1.setContactNumber(912923412);
		stubPatients1.setAddress("la casa del raton");
		stubPatients1.setEmail("yeremi.elraton@gmail.com");
		stubPatients1.setOccupation("Estudiante");
		stubPatients1.setDateOfAdmission(date1);
		stubPatients1.setLifeStory(myFileTest1);
		stubPatients1.setObservations("Esta locuaz");
		
		PatientEntity stubPatients2 = new PatientEntity();
		stubPatients2.setPatientId(1);
		stubPatients2.setFullName("Juancho");
		stubPatients2.setDni(87654321);
		stubPatients2.setAge(28);
		stubPatients2.setContactNumber(987654321);
		stubPatients2.setAddress("2 cm antes de las nubes");
		stubPatients2.setEmail("juancho.elraton@gmail.com");
		stubPatients2.setOccupation("Come Cuates");
		stubPatients2.setDateOfAdmission(date2);
		stubPatients2.setLifeStory(myFileTest2);
		stubPatients2.setObservations("Esta lokazo");
		
		List<PatientEntity> stubPatients = Arrays.asList(stubPatients1, stubPatients2);
		List<PatientDto> patientDtoExpecteds = Arrays.asList(patientDtoExpected1, patientDtoExpected2);

		Mockito.when(patientDaoMock.findAll()).thenReturn(stubPatients);

		List<PatientDto> result = patientService.getAll();

		// verify
		assertNotNull(result);
		assertEquals(patientDtoExpecteds.size(), result.size());
		verify(patientDaoMock, Mockito.times(1)).findAll();
		
		assertEquals(result.get(0).getFullName(), patientDtoExpecteds.get(0).getFullName());
		assertEquals(result.get(0).getDni(), patientDtoExpecteds.get(0).getDni());
		assertEquals(result.get(0).getAge(), patientDtoExpecteds.get(0).getAge());
		assertEquals(result.get(0).getContactNumber(), patientDtoExpecteds.get(0).getContactNumber());
		assertEquals(result.get(0).getAddress(), patientDtoExpecteds.get(0).getAddress());
		assertEquals(result.get(0).getEmail(), patientDtoExpecteds.get(0).getEmail());
		assertEquals(result.get(0).getOccupation(), patientDtoExpecteds.get(0).getOccupation());
		assertEquals(result.get(0).getDateOfAdmission(), patientDtoExpecteds.get(0).getDateOfAdmission());
		assertEquals(result.get(0).getLifeStory(), patientDtoExpecteds.get(0).getLifeStory());
		assertEquals(result.get(0).getObservations(), patientDtoExpecteds.get(0).getObservations());
		
		assertEquals(result.get(1).getFullName(), patientDtoExpecteds.get(1).getFullName());
		assertEquals(result.get(1).getDni(), patientDtoExpecteds.get(1).getDni());
		assertEquals(result.get(1).getAge(), patientDtoExpecteds.get(1).getAge());
		assertEquals(result.get(1).getContactNumber(), patientDtoExpecteds.get(1).getContactNumber());
		assertEquals(result.get(1).getAddress(), patientDtoExpecteds.get(1).getAddress());
		assertEquals(result.get(1).getEmail(), patientDtoExpecteds.get(1).getEmail());
		assertEquals(result.get(1).getOccupation(), patientDtoExpecteds.get(1).getOccupation());
		assertEquals(result.get(1).getDateOfAdmission(), patientDtoExpecteds.get(1).getDateOfAdmission());
		assertEquals(result.get(1).getLifeStory(), patientDtoExpecteds.get(1).getLifeStory());
		assertEquals(result.get(1).getObservations(), patientDtoExpecteds.get(1).getObservations());
	} 
	//
	@Test
	public void patientByIdShouldReturnSuccessfulWhenDaoIsOk() {
		int patientId = 1;
        byte[] myFileTest = new byte[10];
        Date date = new Date("12/01/24");
        
        PatientDto expectedPatientDto = PatientDto.builder()
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
		
		assertEquals(expectedPatientDto.getFullName(), result.getFullName());
        assertEquals(expectedPatientDto.getDni(), result.getDni());
        assertEquals(expectedPatientDto.getAge(), result.getAge());
        assertEquals(expectedPatientDto.getContactNumber() , result.getContactNumber());
        assertEquals(expectedPatientDto.getAddress(), result.getAddress());
        assertEquals(expectedPatientDto.getEmail(), result.getEmail());
        assertEquals(expectedPatientDto.getOccupation(), result.getOccupation());
        assertEquals(expectedPatientDto.getDateOfAdmission(), result.getDateOfAdmission());
        assertArrayEquals(expectedPatientDto.getLifeStory(), result.getLifeStory());
        assertEquals(expectedPatientDto.getObservations(), result.getObservations());     
	}
	
	@Test
	public void shouldUpdateReturnSuccessfulWhenDaoIsOk() {
		int id = 1;
    	byte[] myFileTest = new byte[10];
    	Date date = new Date("12/01/24");
    	
    	PatientDto expectedPatientDto = PatientDto.builder()
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
		
		PatientDto result = patientService.updatePatient(id, expectedPatientDto);
		
		patientService.deletePatient(id);

        verify(patientDaoMock, times(1)).findById(id);
		
		assertEquals(expectedPatientDto.getFullName(), result.getFullName());
        assertEquals(expectedPatientDto.getDni(), result.getDni());
        assertEquals(expectedPatientDto.getAge(), result.getAge());
        assertEquals(expectedPatientDto.getContactNumber() , result.getContactNumber());
        assertEquals(expectedPatientDto.getAddress(), result.getAddress());
        assertEquals(expectedPatientDto.getEmail(), result.getEmail());
        assertEquals(expectedPatientDto.getOccupation(), result.getOccupation());
        assertEquals(expectedPatientDto.getDateOfAdmission(), result.getDateOfAdmission());
        assertArrayEquals(expectedPatientDto.getLifeStory(), result.getLifeStory());
        assertEquals(expectedPatientDto.getObservations(), result.getObservations()); 
		
		
	}
	
	@Test
    public void shouldGetPatientByIdReturnExceptionWhenDaoReturnEmpty() {
		
        int id = 1;
        when(patientDaoMock.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            patientService.getPatientById(id);
        });
	}
}