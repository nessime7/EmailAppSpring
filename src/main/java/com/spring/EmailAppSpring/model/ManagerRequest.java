package com.spring.EmailAppSpring.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ManagerRequest extends Employee {

    private UUID managerId;
    public Set<Employee> employees = new HashSet<>();

    public ManagerRequest(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public ManagerRequest(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", " + super.toString() +
                ", employees=" + employees +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ManagerRequest manager = (ManagerRequest) o;
        return Objects.equals(managerId, manager.managerId) && Objects.equals(employees, manager.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), managerId, employees);
    }

}

