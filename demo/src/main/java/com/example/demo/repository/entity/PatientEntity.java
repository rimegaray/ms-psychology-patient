package com.example.demo.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private int idPatient;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dni")
    private int dni;

    @Column(name = "age")
    private int age;

    @Column(name = "contact_number")
    private int contactNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "date_of_admission")
    private Date dateOfAdmission;

    @Column(name = "life_story")
    private byte[] lifeStory;

    @Column(name = "observations")
    private String observations;
}
