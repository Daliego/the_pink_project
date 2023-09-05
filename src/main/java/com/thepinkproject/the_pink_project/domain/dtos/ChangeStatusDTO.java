package com.thepinkproject.the_pink_project.domain.dtos;

import com.thepinkproject.the_pink_project.domain.enums.Status;
import lombok.*;

import javax.ws.rs.DefaultValue;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class ChangeStatusDTO {
    public String id;
    public Status status;
}
