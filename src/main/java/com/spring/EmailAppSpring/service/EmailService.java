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

    public Company addCompany(CompanyRequest companyRequest) {
        final var company = new Company(UUID.randomUUID(), companyRequest.getName(), companyRequest.getWebsite());

        repository.getCompanies().add(company);
        return company;
    }

    public Company putDepartment(UUID companyId, DepartmentRequest departmentRequest) {
        final var department = new Department(UUID.randomUUID(), departmentRequest.getName(), departmentRequest.getBudget());

//        return repository.getCompanies().stream()
//                .filter(company -> company.getCompanyId().equals(companyId))
//                .peek(company -> company.addDepartment(department))
//                .findFirst()
//                .orElseThrow();

        for (Company company: repository.getCompanies()) {
            if (company.getCompanyId().equals(companyId)){
                company.addDepartment(department);
                return company;
            }
        }
        throw new IllegalStateException();
    }



    public Department putManager(UUID companyId, UUID departmentId, ManagerRequest managerRequest) {
        final var manager = new Manager(UUID.randomUUID(), managerRequest.getFirstName(), managerRequest.getLastName());

        return repository.getCompanies().stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .flatMap(department -> department.getDepartments().stream())
                .filter(department -> department.getDepartmentId().equals(departmentId))
                .peek(department -> department.addManager(manager))
                .findFirst()
                .orElseThrow();
    }

    public Manager putEmployee(UUID companyId, UUID departmentId, UUID managerId, EmployeeRequest employeeRequest) {
        final var employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName());

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
