package com.wro.emailapp.service;

import com.wro.emailapp.config.IdNotFoundException;
import com.wro.emailapp.model.*;
import com.wro.emailapp.repository.DepartmentRepository;
import com.wro.emailapp.repository.EmployeeRepository;
import com.wro.emailapp.repository.ManagerRepository;
import com.wro.emailapp.repository.CompanyRepository;
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
        final var company = companyRepository.findById(companyId).orElseThrow(() -> new IdNotFoundException(companyId));
        final var department = new Department(departmentRequest.getName(), departmentRequest.getBudget(), company);
            departmentRepository.save(department);
            return department;
    }

    public Manager addManager(UUID departmentId, ManagerRequest managerRequest) {
        final var department = departmentRepository.findById(departmentId).orElseThrow(() -> new IdNotFoundException(departmentId));
        final var manager = new Manager(managerRequest.getFirstName(), managerRequest.getLastName(), department);
            managerRepository.save(manager);
            return manager;
    }

    public Employee addEmployee(UUID managerId, EmployeeRequest employeeRequest) {
        final var manager = managerRepository.findById(managerId).orElseThrow(() -> new IdNotFoundException(managerId));
        final var employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName());
            employeeRepository.save(employee);
            return employee;
    }

    public Company patchCompany(UUID companyId, CompanyRequest companyRequest) {
        final var company = companyRepository.findById(companyId).orElseThrow(() -> new IdNotFoundException(companyId));
        if (companyRequest.getWebsite() == null) {
            company.setName(companyRequest.getName());
        }
        else if (companyRequest.getName() == null) {
            company.setWebsite(companyRequest.getWebsite());
        }
        else if (companyRequest.getName() == null && companyRequest.getWebsite() == null) {
            System.out.println("Brak danych!");
        }
        else {
            company.setName(companyRequest.getName());
            company.setWebsite(companyRequest.getWebsite());
        }
        return companyRepository.save(company);
    }

    public Department patchDepartment(UUID departmentId, DepartmentRequest departmentRequest) {
        final var department = departmentRepository.findById(departmentId).get();
        if (departmentRequest.getBudget() == 0) {
            department.setName(departmentRequest.getName());
        }
        else if (departmentRequest.getName() == null) {
            department.setBudget(department.getBudget());
        }
        else if (departmentRequest.getName() == null && departmentRequest.getBudget() == 0) {
            System.out.println("Brak danych!");
        }
        else {
            department.setName(departmentRequest.getName());
            department.setBudget(departmentRequest.getBudget());
        }
        return departmentRepository.save(department);
    }

    public Manager patchManager(UUID managerId, ManagerRequest managerRequest) {
        final var manager = managerRepository.findById(managerId).get();
        if (managerRequest.getLastName() == null) {
            manager.setFirstName(managerRequest.getFirstName());
        }
        else if (managerRequest.getFirstName() == null) {
            manager.setLastName(managerRequest.getLastName());
        }
        else if (managerRequest.getFirstName() == null && managerRequest.getLastName() == null) {
            System.out.println("Brak danych!");
        }
        else {
            manager.setFirstName(managerRequest.getFirstName());
            manager.setLastName(managerRequest.getLastName());
        }
        return managerRepository.save(manager);
    }

    public Employee patchEmployee(UUID employeeId, EmployeeRequest employeeRequest) {
        final var employee = employeeRepository.findById(employeeId).get();
        if (employeeRequest.getLastName() == null) {
            employee.setFirstName(employeeRequest.getFirstName());
        }
        else if (employeeRequest.getFirstName() == null) {
            employee.setLastName(employeeRequest.getLastName());
        }
        else if (employeeRequest.getFirstName() == null && employeeRequest.getLastName() == null) {
            System.out.println("Brak danych!");
        }
        else {
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
        }
        return employeeRepository.save(employee);
    }

    public void deleteCompany(UUID companyId) {
        final var company = companyRepository.findById(companyId).get();
        companyRepository.delete(company);
    }

    public void deleteDepartment(UUID departmentId) {
        final var department = departmentRepository.findById(departmentId).get();
        departmentRepository.delete(department);
    }

    public void deleteManager(UUID managerId) {
        final var manager = managerRepository.findById(managerId).get();
        managerRepository.delete(manager);
    }

    public void deleteEmployee(UUID employeeId) {
        final var employee = employeeRepository.findById(employeeId).get();
        employeeRepository.delete(employee);
    }

    public String generateMail(String firstName, String lastName) {
        return firstName + "." + lastName + "@company.com";
    }

    public String generatePassword() {
        return UUID.randomUUID().toString();
    }
}
