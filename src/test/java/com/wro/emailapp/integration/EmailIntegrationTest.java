package com.wro.emailapp.integration;

import com.wro.emailapp.TestUtils;
import com.wro.emailapp.EmailAppSpringApplication;
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
    public void should_return_companies() {
        given()
            .when().get("/companies")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and().body("name", hasItem("Krzak"))
            .and().body("website", hasItem("krzak.com"));
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
                .and().body("name", is("TestCompany"))
                .and().body("website", is("test-company.com"));
    }

    @Test
    public void should_add_new_department() throws IOException {
        var companyId = "e1649538-a17f-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-department-request.json", CONTEXT))
                .when().post( String.format("/departments/%s", companyId))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void should_add_new_department_and_compare() throws IOException {
        var companyId = "e1649538-a17f-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-department-request.json", CONTEXT))
                .when().post(String.format("/departments/%s", companyId))
                .then()
                .and().body("name", is("TestDepartment"))
                .and().body("budget", is(99999));
    }

    @Test
    public void should_not_add_new_department_and_return_404() throws IOException {
        var companyId = "ab391196-93dd-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-department-request.json", CONTEXT))
                .when().patch( String.format("/companies/%s", companyId))
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void should_add_new_manager() throws IOException {
        var departmentId = "e1649a06-a17f-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-manager-request.json", CONTEXT))
                .when().post(String.format("/managers/%s", departmentId))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void should_add_new_manager_and_compare() throws IOException {
        var departmentId = "e1649a06-a17f-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-manager-request.json", CONTEXT))
                .when().post(String.format("/managers/%s", departmentId))
                .then()
                .and().body("firstName", is("Jan"))
                .and().body("lastName", is("Kowalski"));
    }

    @Test
    public void should_not_add_new_manager_and_return_404() throws IOException {
        var departmentId = "e955625a-adc2-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-manager-request.json", CONTEXT))
                .when().post(String.format("managers/%s", departmentId))
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void should_add_new_employee() throws IOException {
        var managerId = "e164a294-a17f-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-employee-request.json", CONTEXT))
                .when().post(String.format("/employees/%s", managerId))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void should_add_new_employee_and_compare() throws IOException {
        var managerId = "e164a294-a17f-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-employee-request.json", CONTEXT))
                .when().post(String.format("/employees/%s", managerId))
                .then()
                .and().body("firstName", is("Stefan"))
                .and().body("lastName", is("Nowak"));
    }

    @Test
    public void should_not_add_new_employee_and_return_404() throws IOException {
        var managerId = "2fd55f12-adc5-11ec-b909-0242ac120002";
        given().contentType(ContentType.JSON)
                .body(TestUtils.getRequestBodyFromFile("request/add-new-employee-request.json", CONTEXT))
                .when().post(String.format("/employees/%s", managerId))
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
