package com.spring.EmailAppSpring.model;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class CompanyRequest {

    private String name;
    private String website;

    public CompanyRequest(String name, String website) {
        this.name = name;
        this.website = website;
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


    @Override
    public String toString() {
        return "CompanyRequest{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyRequest that = (CompanyRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website);
    }
}

