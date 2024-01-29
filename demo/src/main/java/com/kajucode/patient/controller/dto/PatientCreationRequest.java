package com.kajucode.patient.controller.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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
