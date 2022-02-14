package com.spring.EmailAppSpring.service;

import com.spring.EmailAppSpring.model.*;
import com.spring.EmailAppSpring.storage.EmailAppRepository;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailServiceImplTest {

    private final EmailAppRepository repository = mock(EmailAppRepository.class);

    private final EmailService emailService = new EmailService(repository);

    @Test
    void should_add_new_company() {
        // given
        var name = "Test";
        var website = "www.test.com";

        // when
        var result = emailService.addCompany(new CompanyRequest(name, website));

        // then
        assertEquals("Test", result.getName());
        assertEquals("www.test.com", result.getWebsite());
    }

    @Test
    void should_add_new_department_when_company_exists() {
        // given
        var companyId = UUID.randomUUID();
        var name = "test";
        var budget = 10;
        when(repository.getCompanies()).thenReturn(new HashSet<>(Set.of(new Company(companyId, "Test", "test.com"))));

        // when
        var result = emailService.putDepartment(companyId, new DepartmentRequest(name, budget));

        // then
        var department = result.getDepartments().stream().findFirst().get();
        assertEquals("test", department.getName());
        assertEquals(10, department.getBudget());
    }

    @Test
    void should_add_new_manager_when_department_exists() {
        // given
        var companyId = UUID.randomUUID();
        var departmentId = UUID.randomUUID();
        var firstName = "Test";
        var lastName = "Testo";
        when(repository.getCompanies()).thenReturn(new HashSet<>(Set.of(new Company(companyId, "Test", "test.com",
                new HashSet<>(Set.of(new Department(departmentId, "DepartmentTest", 10)))))));

        // when
        var result = emailService.putManager(companyId, departmentId, new ManagerRequest(firstName, lastName));

        // then
        var manager = result.getManagers().stream().findFirst().get();
        assertEquals("Test", manager.getFirstName());
        assertEquals("Testo", manager.getLastName());
    }



    @Test
    void should_add_new_employee_when_manager_exists() {
        // given
        var companyId = UUID.randomUUID();
        var departmentId = UUID.randomUUID();
        var managerId = UUID.randomUUID();
        var firstName = "Lukasz";
        var lastName = "Testo";
        var mail = new Email();
        when(repository.getCompanies()).thenReturn(new HashSet<>(Set.of(new Company(companyId, "Test", "test.com",
                new HashSet<>(Set.of(new Department(departmentId, "DepartmentTest", 10,
                        new HashSet<>(Set.of(new Manager(managerId, "Test", "Testowski"))))))))));

        // when
        var result = emailService.putEmployee(companyId, departmentId, managerId,
                new EmployeeRequest(firstName, lastName));

        // then
        var employee = result.getEmployees().stream().findFirst().get();
        assertEquals("Lukasz", employee.getFirstName());
        assertEquals("Testo", employee.getLastName());
    }

    @Test
    void should_return_new_email() {
        // given
        var firstName = "Adam";
        var lastName = "Adamowski";

        // when
        var result = emailService.generateMail(firstName, lastName);

        // then
        var expectedMail = "Adam.Adamowski@company.com";
        assertEquals(expectedMail, result);
    }

    @Test
    void should_generate_password() {
        // given
        // when
        var result = emailService.generatePassword();
        // then
        assertNotNull(result);
    }

}

//    TESTY JEDNOSTKOWE - JUNIT
//
//    @Test
//    void should_return_new_email() {
//        // given
//        var firstName = "Adam";
//        var lastName = "Adamowski";
//
//        // when
//        var result = emailService.generateMail(firstName, lastName);
//
//        // then
//        var expectedMail = "Adam.Adamowski@company.com";
//        assertEquals(expectedMail, result);
//    }
//
//    @Test
//    void should_generate_password() {
//        // given
//        // when
//        var result = emailService.generatePassword();
//        // then
//        assertNotNull(result);
//    }
//
//    @Test
//    void should_add_new_company() {
//        // given
//        var name = "Test";
//        var website = "www.test.com";
//
//        // when
//        var result = emailService.addCompany(name, website);
//
//        // then
//        assertEquals("Test", result.getName());
//        assertEquals("www.test.com", result.getWebsite());
//    }
//
//    @Test
//    void should_add_new_department() {
//        // given
//        var companyId = "b7049455-e4b4-42ba-889d-a3d08e9c0eca";
//        var name = "test";
//        var budget = 10;
//
//        // when
//        var result = emailService.addDepartment(UUID.fromString(companyId), name, budget);
//
//        // then
//        assertEquals(1, result.getDepartments().size());
//        assertTrue(result.getDepartments().stream().findFirst().isPresent());
//        var department = result.getDepartments().stream().findFirst().get();
//        assertEquals("test", department.getName());
//        assertEquals(10, department.getBudget());
//        assertTrue(department.getManagers().isEmpty());
//    }
//
//    @Test
//    void should_add_new_manager() {
//        // given
//        var companyId = "b7049455-e4b4-42ba-889d-a3d08e9c0eca";
//        var departmentId = "b48b862e-6a85-11ec-90d6-0242ac120003";
//        var firstName = "Test";
//        var lastName = "Testowski";
//        var login = "test.testowski@company.pl";
//        var password = "1234";
//
//        // when
//        var mailLogin = emailService.generateMail(firstName, lastName);
//        var mailPassword = emailService.generatePassword();
//        var result = emailService.addManager(UUID.fromString(companyId), UUID.fromString(departmentId),
//                firstName, lastName, new Email(mailLogin, mailPassword));
//
//        // then
//    }
