package com.spring.EmailAppSpring.controller;

import com.spring.EmailAppSpring.model.*;
import com.spring.EmailAppSpring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
public class EmailAppController {

    private final EmailService emailService;

    @Autowired
    public EmailAppController(EmailService emailAppService) {
        this.emailService = emailAppService;
    }

    @PostMapping("companies")
    public Company addCompany(@RequestBody CompanyRequest companyRequest) {
        return emailService.addCompany(companyRequest);
    }

    @PutMapping("companies/{companyId}")
    public Company putDepartment(@PathVariable ("companyId") UUID companyId, @RequestBody DepartmentRequest departmentRequest) {
        return emailService.putDepartment(companyId, departmentRequest);
    }

    @PutMapping("companies/{companyId}/{departmentId}")
    public Department putManager(@PathVariable ("companyId") UUID companyId,
                              @PathVariable ("departmentId") UUID departmentId, @RequestBody ManagerRequest managerRequest) {
        return emailService.putManager(companyId, departmentId, managerRequest);
    }

    @PutMapping("companies/{companyId}/{departmentId}/{managerId}")
    public Employee putEmployee(@PathVariable ("companyId") UUID companyId, @PathVariable ("departmentId") UUID departmentId,
                                @PathVariable ("managerId") UUID managerId, @RequestBody EmployeeRequest employeeRequest) {
        return emailService.putEmployee(companyId, departmentId, managerId, employeeRequest);
    }

    @GetMapping("companies/{companyId}")
    public Company getAllCompanies(@PathVariable ("companyId") UUID companyId) {
        return emailService.getAllCompanies(companyId);
    }

    @GetMapping("companies")
    public Set<Company> getCompany() {
        return emailService.getCompany();
    }

    @GetMapping("companies/{companyId}/{departmentId}")
    public void getDepartment(@PathVariable ("companyId") UUID companyId, @PathVariable ("departmentId") UUID departmentId) {
        emailService.getDepartment(companyId, departmentId);
    }

    @GetMapping("companies/{companyId}/{departmentId}/{managerId}")
    public void getManager(@PathVariable ("companyId") UUID companyId, @PathVariable ("departmentId") UUID departmentId,
                              @PathVariable ("mangerId") UUID managerId) {
        emailService.getManager(companyId, departmentId, managerId);
    }

}
