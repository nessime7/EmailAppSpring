package com.spring.EmailAppSpring.service;

import com.spring.EmailAppSpring.model.*;
import com.spring.EmailAppSpring.repository.CompanyRepository;
import com.spring.EmailAppSpring.repository.DepartmentRepository;
import com.spring.EmailAppSpring.repository.EmployeeRepository;
import com.spring.EmailAppSpring.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Company addCompany(CompanyRequest companyRequest) {
        final var company = new Company(companyRequest.getName(), companyRequest.getWebsite());
        companyRepository.save(company);
        return company;
    }

    public Company addDepartment(UUID companyId, DepartmentRequest departmentRequest) {
        final var company = companyRepository.getById(companyId);
        final var department = new Department(departmentRequest.getName(), departmentRequest.getBudget(), company);
        departmentRepository.save(department);
        return company;
    }


    public Department addManager(UUID departmentId, ManagerRequest managerRequest) {
        final var department = departmentRepository.getById(departmentId);
        final var manager = new Manager(managerRequest.getFirstName(), managerRequest.getLastName(), department);
        managerRepository.save(manager);
        return department;
    }

    public Manager addEmployee(UUID managerId, EmployeeRequest employeeRequest) {
        final var manager = managerRepository.getById(managerId);
        final var employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName());
        employeeRepository.save(employee);
        return manager;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

//    public Set<Company> getCompany() {
//        return repository.getCompanies();
//    }
//    public void getDepartment(UUID companyId, UUID departmentId) {
//        repository.getCompanies().stream()
//                .filter(company -> company.getCompanyId().equals(companyId))
//                .flatMap(department -> department.getDepartments().stream())
//                .filter(department -> department.getDepartmentId().equals(departmentId))
//                .forEach(System.out::println);
//    }
//
//    public void getManager(UUID companyId, UUID departmentId, UUID managerId) {
//        repository.getCompanies().stream()
//                .filter(company -> company.getCompanyId().equals(companyId))
//                .flatMap(department -> department.getDepartments().stream())
//                .filter(department -> department.getDepartmentId().equals(departmentId))
//                .flatMap(department -> department.getManagers().stream())
//                .filter(department -> department.getManagerId().equals(managerId))
//                .forEach(System.out::println);
//    }
//
//    public String generateMail(String firstName, String lastName) {
//        return firstName + "." + lastName + "@company.com";
//    }
//
//    public String generatePassword() {
//        return UUID.randomUUID().toString();
//    }

}
