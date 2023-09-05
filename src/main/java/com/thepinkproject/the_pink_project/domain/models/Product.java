package com.thepinkproject.the_pink_project.domain.models;

import com.thepinkproject.the_pink_project.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Range;

import java.time.LocalDate;

//import javax.persistance.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status availability;

    @Column(nullable = false)
    private String destination;

    @Range(from = 0, to = 20)
    @Column(nullable = false)
    private Integer rentabilityTax;

    @Range(from = 0, to = 48)
    @Column(nullable = false)
    private Integer minPeriod;

    @Column(nullable = false)
    private Integer administrationTax;

    @NonNull
    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private Boolean dailyLiquidity;

//    public Boolean getDailyLiquidity() {
//        return this.getDailyLiquidity();
//    }
}
