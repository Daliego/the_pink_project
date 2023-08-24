package com.thepinkproject.the_pink_project.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

//import javax.persistance.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", uniqueConstraints = { @UniqueConstraint(columnNames = {"name"}) })
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "destination")
    private String destination;

    @Column(name = "rentabilityTax")
    private Integer rentabilityTax;

    @Column(name = "minimunTime")
    private Integer minimumTime;

    @Column(name = "administrationTax")
    private Integer administrationTax;

    @Column(name = "vencimento")
    private LocalDate vencimento;
}
