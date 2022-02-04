package com.spring.EmailAppSpring.service;

import com.spring.EmailAppSpring.model.*;
import com.spring.EmailAppSpring.storage.EmailAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class EmailService {

    private final EmailAppRepository repository;

    @Autowired
    public EmailService(EmailAppRepository repository) {
        this.repository = repository;
    }

    public Company addCompany(Company company) {
        repository.getCompanies().add(company);
        return company;
    }

    public Company putDepartment(UUID companyId, Department department) {
        return repository.getCompanies().stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .peek(company -> company.addDepartment(department))
                .findFirst()
                .orElseThrow();
    }

    public Department putManager(UUID companyId, UUID departmentId, Manager manager) {
        return repository.getCompanies().stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .flatMap(department -> department.getDepartments().stream())
                .filter(department -> department.getDepartmentId().equals(departmentId))
                .peek(department -> department.addManager(manager))
                .findFirst()
                .orElseThrow();
    }

    public Manager putEmployee(UUID companyId, UUID departmentId, UUID managerId, Employee employee) {
        return repository.getCompanies().stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .flatMap(department -> department.getDepartments().stream())
                .filter(department -> department.getDepartmentId().equals(departmentId))
                .flatMap(manager -> manager.getManagers().stream())
                .filter(manager -> manager.getManagerId().equals(managerId))
                .peek(addEmployee -> addEmployee.addEmployee(employee))
                .findFirst()
                .orElseThrow();
    }

    public Company getAllCompanies(UUID companyId) {
        return repository.getCompanies().stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElseThrow();
    }

    public Set<Company> getCompany() {
        return repository.getCompanies();
    }

    public void getDepartment(UUID companyId, UUID departmentId) {
        repository.getCompanies().stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .flatMap(department -> department.getDepartments().stream())
                .filter(department -> department.getDepartmentId().equals(departmentId))
                .forEach(System.out::println);
    }

    public void getManager(UUID companyId, UUID departmentId, UUID managerId) {
        repository.getCompanies().stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .flatMap(department -> department.getDepartments().stream())
                .filter(department -> department.getDepartmentId().equals(departmentId))
                .flatMap(department -> department.getManagers().stream())
                .filter(department -> department.getManagerId().equals(managerId))
                .forEach(System.out::println);
    }

    public String generateMail(String firstName, String lastName) {
        return firstName + "." + lastName + "@company.com";
    }

    public String generatePassword() {
        return UUID.randomUUID().toString();
    }

}
