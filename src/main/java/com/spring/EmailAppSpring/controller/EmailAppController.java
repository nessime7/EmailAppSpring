package com.spring.EmailAppSpring.controller;

import com.spring.EmailAppSpring.model.*;
import com.spring.EmailAppSpring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Company addDepartment(@PathVariable ("companyId") UUID companyId, @RequestBody DepartmentRequest departmentRequest) {
        return emailService.addDepartment(companyId, departmentRequest);
    }

    @PutMapping("companies/{departmentId}")
    public Department addManager(@PathVariable ("departmentId") UUID departmentId, @RequestBody ManagerRequest managerRequest) {
        return emailService.addManager(departmentId, managerRequest);
    }

    @PutMapping("companies/{managerId}")
    public Manager addEmployee(@PathVariable ("managerId") UUID managerId, @RequestBody EmployeeRequest employeeRequest) {
        return emailService.addEmployee(managerId, employeeRequest);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies() {
        return emailService.getAllCompanies();
    }

//    @GetMapping("companies")
//    public Set<Company> getCompany() {
//        return emailService.getCompany();
//    }
//
//    @GetMapping("companies/{companyId}/{departmentId}")
//    public void getDepartment(@PathVariable ("companyId") UUID companyId, @PathVariable ("departmentId") UUID departmentId) {
//        emailService.getDepartment(companyId, departmentId);
//    }
//
//    @GetMapping("companies/{companyId}/{departmentId}/{managerId}")
//    public void getManager(@PathVariable ("companyId") UUID companyId, @PathVariable ("departmentId") UUID departmentId,
//                              @PathVariable ("mangerId") UUID managerId) {
//        emailService.getManager(companyId, departmentId, managerId);
//    }

}
