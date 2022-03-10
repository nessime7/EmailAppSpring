package com.spring.EmailAppSpring.controller;

import com.spring.EmailAppSpring.model.*;
import com.spring.EmailAppSpring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EmailAppController {

    private final EmailService emailService;

    @Autowired
    public EmailAppController(EmailService emailAppService) {
        this.emailService = emailAppService;
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies() {
        return emailService.getAllCompanies();
    }

    @GetMapping("companies/{companyId}")
    public Optional<Company> getCompany(@PathVariable ("companyId") UUID companyId) {
        return emailService.getCompany(companyId);
    }

    @GetMapping("departments/{departmentId}")
    public Optional<Department> getDepartment(@PathVariable ("departmentId") UUID departmentId) {
        return emailService.getDepartment(departmentId);
    }

    @GetMapping("managers/{managerId}")
    public Optional<Manager> getManager(@PathVariable ("mangerId") UUID managerId) {
        return emailService.getManager(managerId);
    }

    @PostMapping("companies")
    public ResponseEntity<Company> addCompany(@RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(emailService.addCompany(companyRequest));
    }

    @PostMapping("departments/{companyId}")
    public Department addDepartment(@PathVariable ("companyId") UUID companyId, @RequestBody DepartmentRequest departmentRequest) {
        return emailService.addDepartment(companyId, departmentRequest);
    }

    @PostMapping("managers/{departmentId}")
    public Manager addManager(@PathVariable ("departmentId") UUID departmentId, @RequestBody ManagerRequest managerRequest) {
        return emailService.addManager(departmentId, managerRequest);
    }

    @PostMapping("employees/{managerId}")
    public Employee addEmployee(@PathVariable ("managerId") UUID managerId, @RequestBody EmployeeRequest employeeRequest) {
        return emailService.addEmployee(managerId, employeeRequest);
    }

}
