package com.kajucode.patient.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;



@Builder
@Getter
@Setter
public class PatientDto implements Serializable{
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
