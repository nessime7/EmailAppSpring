package com.spring.EmailAppSpring.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;

    private String website;

    public Company(String name, String website) {
        this.name = name;
        this.website = website;
    }

}

