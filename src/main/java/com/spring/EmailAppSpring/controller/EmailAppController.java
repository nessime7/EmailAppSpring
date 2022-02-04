package com.spring.EmailAppSpring.controller;

import com.spring.EmailAppSpring.model.Company;
import com.spring.EmailAppSpring.model.Department;
import com.spring.EmailAppSpring.model.Employee;
import com.spring.EmailAppSpring.model.Manager;
import com.spring.EmailAppSpring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@Controller
public class EmailAppController {

    private final EmailService emailService;

    @Autowired
    public EmailAppController(EmailService emailAppService) {
        this.emailService = emailAppService;
    }

    @PostMapping("companies")
    public Company addCompany(Company company) {
        return emailService.addCompany(company);
    }

    @PutMapping("companies/{companyId}")
    public Company putDepartment(@PathVariable ("companyId") UUID companyId, @RequestBody Department department) {
        return emailService.putDepartment(companyId, department);
    }

    @PutMapping("companies/{companyId}/{departmentId}")
    public Department putManager(@PathVariable ("companyId") UUID companyId,
                              @PathVariable ("departmentId") UUID departmentId, @RequestBody Manager manager) {
        return emailService.putManager(companyId, departmentId, manager);
    }

    @PutMapping("companies/{companyId}/{departmentId}/{managerId}")
    public Employee putEmployee(@PathVariable ("companyId") UUID companyId, @PathVariable ("departmentId") UUID departmentId,
                                @PathVariable ("mangerId") UUID managerId, @RequestBody Employee employee) {
        return emailService.putEmployee(companyId, departmentId, managerId, employee);
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
