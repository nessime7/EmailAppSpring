package com.spring.EmailAppSpring.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Manager extends Employee {

    private UUID managerId;
    public Set<Employee> employees = new HashSet<>();

    public Manager(UUID managerId, String firstName, String lastName, Email email, Set<Employee> employees) {
        super(firstName, lastName, email);
        this.managerId = managerId;
        this.employees = employees;
    }

    public Manager(UUID managerId, String firstName, String lastName, Email email) {
        super(firstName, lastName, email);
        this.managerId = managerId;
    }

    public Manager() {

    }

    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }

    public Manager(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        if (this.employees != null) {
            this.employees.add(employee);
        }
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
        Manager manager = (Manager) o;
        return Objects.equals(managerId, manager.managerId) && Objects.equals(employees, manager.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), managerId, employees);
    }

}

