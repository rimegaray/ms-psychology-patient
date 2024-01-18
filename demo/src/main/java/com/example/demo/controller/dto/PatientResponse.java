package com.example.demo.controller.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PatientResponse {
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
