package com.spring.EmailAppSpring.repository;

import com.spring.EmailAppSpring.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//public interface EquipmentRepository extends JpaRepository<Equipment, UUID>

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {


}
