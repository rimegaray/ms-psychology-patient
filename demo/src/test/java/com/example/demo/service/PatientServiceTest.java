package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.repository.dao.PatientDao;
import com.example.demo.repository.dto.PatientDto;
import com.example.demo.repository.entity.PatientEntity;

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
	public void addProductShouldReturnSuccessfulWhenDaoIsOk() {
		// Precondiciones
		byte[] myFileTest = new byte[10];

		PatientDto patientDtoExpected = new PatientDto();
		patientDtoExpected.setFullName("Yeremi");
		patientDtoExpected.setDni(76351126);
		patientDtoExpected.setAge(17);
		patientDtoExpected.setContactNumber(912923412);
		patientDtoExpected.setAddress("la casa del raton");
		patientDtoExpected.setEmail("yeremi.elraton@gmail.com");
		patientDtoExpected.setOccupation("Estudiante");
		patientDtoExpected.setDateOfAdmission(new Date("12/01/24"));
		patientDtoExpected.setLifeStory(myFileTest);
		patientDtoExpected.setObservations("Esta locuaz");

		PatientEntity patientEntity = new PatientEntity();
		patientEntity.setIdPatient(0);
		patientEntity.setFullName("Yeremi");
		patientEntity.setDni(76351126);
		patientEntity.setAge(17);
		patientEntity.setContactNumber(912923412);
		patientEntity.setAddress("la casa del raton");
		patientEntity.setEmail("yeremi.elraton@gmail.com");
		patientEntity.setOccupation("Estudiante");
		patientEntity.setDateOfAdmission(new Date("12/01/24"));
		patientEntity.setLifeStory(myFileTest);
		patientEntity.setObservations("Esta locuaz");

		PatientEntity patientEntityResult = new PatientEntity();
		patientEntityResult.setIdPatient(0);
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
		PatientDto patientDto = patientService.addProduct(patientEntity);

		// Asserts
		assertNotNull(patientDto);
		assertEquals(patientDto.getFullName(), patientDtoExpected.getFullName());
		assertEquals(patientDto.getFullName(), patientDtoExpected.getFullName());
		assertEquals(patientDto.getDni(), patientDtoExpected.getDni());
		assertEquals(patientDto.getAge(), patientDtoExpected.getAge());
		assertEquals(patientDto.getContactNumber(), patientDtoExpected.getContactNumber());
		assertEquals(patientDto.getAddress(), patientDtoExpected.getAddress());
		assertEquals(patientDto.getEmail(), patientDtoExpected.getEmail());
		assertEquals(patientDto.getOccupation(), patientDtoExpected.getOccupation());
		assertEquals(patientDto.getDateOfAdmission(), patientDtoExpected.getDateOfAdmission());
		assertEquals(patientDto.getLifeStory(), patientDtoExpected.getLifeStory());
		assertEquals(patientDto.getObservations(), patientDtoExpected.getObservations());
	}

	@Test
	public void testDeletePatient() {
		
		//precondiciones
		Mockito.doNothing().when(patientDaoMock).deleteById(Mockito.anyInt());
		//ejecucion real
		patientService.deletePacient(123);
		//verificaciones
		Mockito.verify(patientDaoMock, Mockito.times(1)).deleteById(123);
	}

	@Test
	public void testGetAll() {
		byte[] myFileTest = new byte[10];
		
		PatientEntity patientEntity = new PatientEntity();
		patientEntity.setIdPatient(0);
		patientEntity.setFullName("Yeremi");
		patientEntity.setDni(76351126);
		patientEntity.setAge(17);
		patientEntity.setContactNumber(912923412);
		patientEntity.setAddress("la casa del raton");
		patientEntity.setEmail("yeremi.elraton@gmail.com");
		patientEntity.setOccupation("Estudiante");
		patientEntity.setDateOfAdmission(new Date("12/01/24"));
		patientEntity.setLifeStory(myFileTest);
		patientEntity.setObservations("Esta locuaz");
		
		List<PatientEntity> samplePatients = Arrays.asList(patientEntity, patientEntity);

		Mockito.when(patientDaoMock.findAll()).thenReturn(samplePatients);

		List<PatientEntity> result = patientService.getAll();

		// verify
		assertNotNull(result);
		assertEquals(samplePatients.size(), result.size());
		Mockito.verify(patientDaoMock, Mockito.times(1)).findAll();
		assertTrue(result.containsAll(samplePatients) && samplePatients.containsAll(result));
	}

}