package com.thepinkproject.the_pink_project.domain.dtos;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private Boolean availability;
    private String destination;
    private Integer rentabilityTax;
    private Integer minimunTime;
    private Integer administrationTax;
}
