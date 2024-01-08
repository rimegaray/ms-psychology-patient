package com.example.demo.repository.dao;

import com.example.demo.repository.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao  extends JpaRepository<PatientEntity, Integer>{
}
