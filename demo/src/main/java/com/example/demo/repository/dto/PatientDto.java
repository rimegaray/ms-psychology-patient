package com.example.demo.repository.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientDto {
    String fullName;
    int dni;
    int age;
    int contactNumber;
    String address;
    String email;
    String occupation;
    Date dateOfAdmission;
    byte[] lifeStory;
    String observations;
}
