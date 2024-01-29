package com.kajucode.patient.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kajucode.patient.repository.entity.PatientEntity;

@Repository
public interface PatientDao  extends JpaRepository<PatientEntity, Integer>{
}
