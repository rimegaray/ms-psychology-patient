package com.kajucode.patient.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kajucode.patient.repository.entity.PsychologistEntity;

@Repository
public interface PatientDao  extends JpaRepository<PsychologistEntity, Integer>{
}
