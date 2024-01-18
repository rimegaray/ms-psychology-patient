package com.kajucode.patient.controller.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientCreationRequest {
    private String fullName;
    private int dni;
    private int age;
    private int contactNumber;
    private String address;
    private String email;
    private String occupation;
    private Date dateOfAdmission;
    private byte[] lifeStory;
    private String observations;

}
