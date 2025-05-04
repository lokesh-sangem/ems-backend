package com.tac.ems_backend.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {// we use employee dto class to transfer the data b/w client and server.
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
