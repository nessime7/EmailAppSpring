package com.spring.EmailAppSpring.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Department {

    private UUID departmentId;
    private String name;
    private int budget;
    public Set<Manager> managers = new HashSet<>();

    public Department(UUID departmentId, String name, int budget, Set<Manager> managers) {
        this.departmentId = departmentId;
        this.name = name;
        this.budget = budget;
        this.managers = managers;
    }

    public Department(UUID departmentId, String name, int budget) {
        this.departmentId = departmentId;
        this.name = name;
        this.budget = budget;
    }

    public Department() {

    }

    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
    }

    public void addManager(Manager manager) {
        this.managers.add(manager);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", managers=" + managers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return budget == that.budget && Objects.equals(departmentId, that.departmentId) && Objects.equals(name, that.name) && Objects.equals(managers, that.managers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, name, budget, managers);
    }

}