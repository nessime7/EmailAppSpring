package com.wro.emailapp.repository;

import com.wro.emailapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//public interface EquipmentRepository extends JpaRepository<Equipment, UUID>

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {


}
