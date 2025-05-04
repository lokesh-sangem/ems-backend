package com.tac.ems_backend.mapper;

import com.tac.ems_backend.dto.EmployeeDTO;
import com.tac.ems_backend.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDto(Employee employee){
        if (employee == null) {
            return null; // Return null if the input is null
        }
        return new EmployeeDTO(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDto){
        if (employeeDto == null) {
            return null; // Return null if the input is null
        }
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
