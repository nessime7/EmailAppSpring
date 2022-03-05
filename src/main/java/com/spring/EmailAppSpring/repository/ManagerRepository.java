package com.spring.EmailAppSpring.repository;

import com.spring.EmailAppSpring.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//public interface EquipmentRepository extends JpaRepository<Equipment, UUID>

@Repository
public interface ManagerRepository extends JpaRepository<Manager, UUID> {


}
