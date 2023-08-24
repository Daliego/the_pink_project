package com.thepinkproject.the_pink_project.domain.dtos;

import lombok.*;

import javax.ws.rs.DefaultValue;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class ChangeStatusDTO {
    public String id;
    public Boolean status;
}
