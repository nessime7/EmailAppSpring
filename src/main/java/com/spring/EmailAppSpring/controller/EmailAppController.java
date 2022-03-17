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

    @PatchMapping("companies/{companyId}")
    public Company patchCompany(@PathVariable ("companyId") UUID companyId, @RequestBody CompanyRequest companyRequest) {
        return emailService.patchCompany(companyId, companyRequest);
    }

    @PatchMapping("departments/{departmentId}")
    public Department patchDepartment(@PathVariable ("departmentId") UUID departmentId, @RequestBody DepartmentRequest departmentRequest) {
        return emailService.patchDepartment(departmentId, departmentRequest);
    }

    @PatchMapping("managers/{managerId}")
    public Manager patchManager(@PathVariable ("managerId") UUID managerId, @RequestBody ManagerRequest managerRequest) {
        return emailService.patchManager(managerId, managerRequest);
    }

    @PatchMapping("employees/{employeeId}")
    public Employee patchEmployee(@PathVariable ("employeeId") UUID employeeId, @RequestBody EmployeeRequest employeeRequest) {
        return emailService.patchEmployee(employeeId, employeeRequest);
    }

    @DeleteMapping("companies/{companyId}")
    public void deleteCompany(@PathVariable ("companyId") UUID companyId) {
        emailService.deleteCompany(companyId);
    }

    @DeleteMapping("departments/{departmentId}")
    public void deleteDepartment(@PathVariable ("departmentId") UUID departmentId) {
        emailService.deleteDepartment(departmentId);
    }

    @DeleteMapping("managers/{managerId}")
    public void deleteManager(@PathVariable ("managerId") UUID managerId) {
        emailService.deleteManager(managerId);
    }

    @DeleteMapping("employees/{employeeId}")
    public void deleteEmployee(@PathVariable ("employeeId") UUID employeeId) {
        emailService.deleteEmployee(employeeId);
    }

}
