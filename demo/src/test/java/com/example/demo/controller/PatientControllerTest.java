package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kajucode.patient.controller.PatientController;
import com.kajucode.patient.repository.entity.PatientEntity;
import com.kajucode.patient.service.PatientService;
import com.kajucode.patient.service.dto.PatientDto;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
	@Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;
    
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

    // Puedes agregar más pruebas según sea necesario
}
}