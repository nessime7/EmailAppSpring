package com.spring.EmailAppSpring.storage;

import com.spring.EmailAppSpring.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Repository
public class EmailAppRepository {

    private Set<Company> companies = new HashSet<>(Set.of(
            new Company(UUID.fromString("b7049455-e4b4-42ba-889d-a3d08e9c0eca"), "Test Company", "www.company.test",
                    new HashSet<>(Set.of(new Department(UUID.fromString("b48b862e-6a85-11ec-90d6-0242ac120003"),
                            "TestOffice", 10000,
                            new HashSet<>(Set.of(new Manager(UUID.fromString("1c0d10a6-6b03-11ec-90d6-0242ac120003"),
                                    "Adam", "Adamowski", new Email("Adam.Adamowski@test.com", "1234"),
                                    new HashSet<>(Set.of(new Employee("Jan", "Kowalski",
                                            new Email("Jan.Kowalski@test.com", "1234"))
                                    )))))))))));

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "EmailAppRepository{" +
                "companies=" + companies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAppRepository that = (EmailAppRepository) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies);
    }

}
