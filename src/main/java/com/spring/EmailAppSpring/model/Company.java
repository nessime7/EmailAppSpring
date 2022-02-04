package com.spring.EmailAppSpring.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Company {

    private UUID companyId;
    private String name;
    private String website;
    private Set<Department> departments = new HashSet<>();

    public Company(UUID companyId, String name, String website, Set<Department> departments) {
        this.companyId = companyId;
        this.name = name;
        this.website = website;
        this.departments = departments;
    }

    public Company(UUID companyId, String name, String website) {
        this.companyId = companyId;
        this.name = name;
        this.website = website;
    }

    public Company() {

    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> department) {
        this.departments = departments;
    }

    public void addDepartment(Department department) {
            this.departments.add(department);
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", departments=" + departments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyId, company.companyId) && Objects.equals(name, company.name) && Objects.equals(website, company.website) && Objects.equals(departments, company.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, name, website, departments);
    }

}

