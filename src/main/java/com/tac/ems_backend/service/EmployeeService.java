package com.tac.ems_backend.service;

import com.tac.ems_backend.dto.EmployeeDTO;

import java.util.Map;

public interface EmployeeService {
   Map<String,Object> createEmployee(EmployeeDTO employeeDto);

   Map<String,Object> getEmployeeById(Long id);

   Map<String,Object>getEmployees();

   Map<String,Object> deleteEmployee(Long id);

   Map<String,Object> updateEmployee(Long id, EmployeeDTO employeeDTO);
}
