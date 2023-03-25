package com.wro.emailapp.model;

import java.util.Objects;

public class DepartmentRequest {

    private String name;
    private int budget;

    public DepartmentRequest(String name, int budget) {
        this.name = name;
        this.budget = budget;
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

    @Override
    public String toString() {
        return "DepartmentRequest{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentRequest that = (DepartmentRequest) o;
        return budget == that.budget && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, budget);
    }
}