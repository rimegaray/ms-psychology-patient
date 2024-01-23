package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kajucode.patient.repository.dao.PatientDao;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.PatientService;
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
	public void addProductShouldReturnSuccessfulWhenDaoIsOk() {
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
		byte[] myFileTest = new byte[10];
		
		PatientEntity patientEntity = new PatientEntity();
		patientEntity.setPatientId(0);
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

		List<PatientDto> result = patientService.getAll();

		// verify
		assertNotNull(result);
		assertEquals(samplePatients.size(), result.size());
		Mockito.verify(patientDaoMock, Mockito.times(1)).findAll();
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
		
	}

}