package com.wro.emailapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;

    private int budget;

    @ManyToOne
    private Company company;

    public Department(String name, int budget, Company company) {
        this.name = name;
        this.budget = budget;
        this.company = company;
    }

}