package com.spring.EmailAppSpring.integration;

import com.spring.EmailAppSpring.EmailAppSpringApplication;
import com.spring.EmailAppSpring.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EmailAppSpringApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailIntegrationTest {

    @LocalServerPort
    private int port;
    private static final String CONTEXT = "companies";

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void should_return_companies() throws IOException {
        given()
            .when().get("/companies")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and().body("", equalTo(TestUtils.getPath("response/companies-response.json", CONTEXT)
                        .getList("")));
    }

    @Test
    public void should_add_new_company() throws IOException {
        given().contentType(ContentType.JSON).body(TestUtils.
                getRequestBodyFromFile("request/add-new-company-request.json", CONTEXT))
                .when().post("/companies")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void should_add_new_company_and_compare() throws IOException {
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-company-request.json", CONTEXT))
                .when().post("/companies")
                .then()
                .and().body("name", is("Company"))
                .and().body("website", is("company.com"));
    }

    @Test
    public void should_add_new_department() throws IOException {
        var companyId = "b7049455-e4b4-42ba-889d-a3d08e9c0eca";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-department-request.json", CONTEXT))
                .when().put( String.format("/companies/%s", companyId))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void should_add_new_department_and_compare() throws IOException {
        var companyId = "b7049455-e4b4-42ba-889d-a3d08e9c0eca";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-department-request.json", CONTEXT))
                .when().put(String.format("/companies/%s", companyId))
                .then()
                .and().body("departments.name", hasItem("TestDepartment"))
                .and().body("departments.budget", hasItem(99999));
    }

    @Test
    public void should_add_new_manager() throws IOException {
        var companyId = "b7049455-e4b4-42ba-889d-a3d08e9c0eca";
        var departmentId = "b48b862e-6a85-11ec-90d6-0242ac120003";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-manager-request.json", CONTEXT))
                .when().put(String.format("/companies/%s/%s", companyId, departmentId))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void should_add_new_manager_and_compare() throws IOException {
        var companyId = "b7049455-e4b4-42ba-889d-a3d08e9c0eca";
        var departmentId = "b48b862e-6a85-11ec-90d6-0242ac120003";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-manager-request.json", CONTEXT))
                .when().put(String.format("/companies/%s/%s", companyId, departmentId))
                .then()
                .and().body("managers.firstName", hasItem("Jan"))
                .and().body("managers.lastName", hasItem("Kowalski"));
    }

    @Test
    public void should_add_new_employee() throws IOException {
        var companyId = "b7049455-e4b4-42ba-889d-a3d08e9c0eca";
        var departmentId = "b48b862e-6a85-11ec-90d6-0242ac120003";
        var managerId = "1c0d10a6-6b03-11ec-90d6-0242ac120003";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-employee-request.json", CONTEXT))
                .when().put(String.format("/companies/%s/%s/%s", companyId, departmentId, managerId))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void should_add_new_employee_and_compare() throws IOException {
        var companyId = "b7049455-e4b4-42ba-889d-a3d08e9c0eca";
        var departmentId = "b48b862e-6a85-11ec-90d6-0242ac120003";
        var managerId = "1c0d10a6-6b03-11ec-90d6-0242ac120003";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-employee-request.json", CONTEXT))
                .when().put(String.format("/companies/%s/%s/%s", companyId, departmentId, managerId))
                .then()
                .and().body("employees.firstName", hasItem("Stefan"))
                .and().body("employees.lastName", hasItem("Nowak"));
    }

}
