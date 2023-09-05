package com.thepinkproject.the_pink_project.domain.dtos;

import com.thepinkproject.the_pink_project.domain.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private Status availability;
    private String destination;
    private Integer rentabilityTax;
    private Integer minPeriod;
    private Integer administrationTax;
    private LocalDate expirationDate;
    private Boolean dailyLiquidity;
}
