package com.spring.EmailAppSpring.service;

import com.spring.EmailAppSpring.model.*;
import com.spring.EmailAppSpring.repository.CompanyRepository;
import com.spring.EmailAppSpring.repository.DepartmentRepository;
import com.spring.EmailAppSpring.repository.EmployeeRepository;
import com.spring.EmailAppSpring.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmailService(CompanyRepository companyRepository, DepartmentRepository departmentRepository, ManagerRepository managerRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.departmentRepository = departmentRepository;
        this.managerRepository = managerRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompany(UUID companyId) {
        return companyRepository.findById(companyId);
    }

    public Optional<Department> getDepartment(UUID departmentId) {
        return departmentRepository.findById(departmentId);
    }

    public Optional<Manager> getManager(UUID managerId) {
        return managerRepository.findById(managerId);
    }

    public Company addCompany(CompanyRequest companyRequest) {
        final var company = new Company(companyRequest.getName(), companyRequest.getWebsite());
        companyRepository.save(company);
        return company;
    }

    public Department addDepartment(UUID companyId, DepartmentRequest departmentRequest) {
        final var company = companyRepository.findById(companyId).get();
        final var department = new Department(departmentRequest.getName(), departmentRequest.getBudget(), company);
        departmentRepository.save(department);
        return department;
    }

    public Manager addManager(UUID departmentId, ManagerRequest managerRequest) {
        final var department = departmentRepository.findById(departmentId).get();
        final var manager = new Manager(managerRequest.getFirstName(), managerRequest.getLastName(), department);
        managerRepository.save(manager);
        return manager;
    }

    public Employee addEmployee(UUID managerId, EmployeeRequest employeeRequest) {
        final var manager = managerRepository.findById(managerId).get();
        final var employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName());
        employeeRepository.save(employee);
        return employee;
    }
    
    public String generateMail(String firstName, String lastName) {
        return firstName + "." + lastName + "@company.com";
    }

    public String generatePassword() {
        return UUID.randomUUID().toString();
    }

}
