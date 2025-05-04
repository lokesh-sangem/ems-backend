package com.tac.ems_backend.impl;

import com.tac.ems_backend.dto.EmployeeDTO;
import com.tac.ems_backend.entity.Employee;
import com.tac.ems_backend.exception.ResourceNotFoundException;
import com.tac.ems_backend.mapper.EmployeeMapper;
import com.tac.ems_backend.repository.EmployeeRepository;
import com.tac.ems_backend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;
    @Override
    public Map<String,Object> createEmployee(EmployeeDTO employeeDto) {
        if(employeeDto==null){
            throw new RuntimeException("EmployeeDTO cannot be null");
        }
        Employee employee=EmployeeMapper.mapToEmployee(employeeDto);
       Employee savedEmployee =employeeRepo.save(employee);
       EmployeeDTO savedEmployeeDTO=EmployeeMapper.mapToEmployeeDto(savedEmployee);
        Map<String,Object> response= Map.of(
                "message","Employee created successfully",
                "data",savedEmployeeDTO);
        return response;
    }

    @Override
    public Map<String, Object> getEmployeeById(Long employeeId) {
        if(employeeId==null){
            throw new RuntimeException("Employee ID cannot be null");
        }
        Employee employee=employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not found with given Id:"+employeeId));

        return Map.of("message","Employee found successfully","data",EmployeeMapper.mapToEmployeeDto(employee));

    }

    @Override
    public Map<String, Object> getEmployees() {
        List<Employee>listofEmployees=employeeRepo.findAll();
        if(listofEmployees.isEmpty()){
            throw new ResourceNotFoundException("No employees found");
        }
        List<EmployeeDTO>employeeDTOList = listofEmployees.stream().map(employee -> EmployeeMapper.mapToEmployeeDto((employee))).collect(Collectors.toList());
        return Map.of("message","List of employees found successfully","data",employeeDTOList);
    }

    @Override
    public Map<String,Object> deleteEmployee(Long id) {
        if(id==null){
            throw new RuntimeException("Employee ID cannot be null");
        }
        Employee employee =employeeRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not found with given Id:"+id));
        employeeRepo.delete(employee);
        return Map.of("message","Employee deleted successfully",
                "employeeId",id);
    }

    @Override
    public Map<String,Object> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        if(id==null){
            throw new RuntimeException("Employee ID cannot be null");
        }
        Employee employee=employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with given Id:"+id));

        if(employeeDTO.getLastName()!=null && !employeeDTO.getLastName().isBlank()){
            employee.setLastName(employeeDTO.getLastName());
        }
        if(employeeDTO.getFirstName()!=null&&!employeeDTO.getFirstName().isBlank()){
            employee.setFirstName(employeeDTO.getFirstName());
        }
        if(employeeDTO.getEmail()!=null&&!employeeDTO.getEmail().isBlank()){
            employee.setEmail(employeeDTO.getEmail());
        }
        Employee updatedEmployee = employeeRepo.save(employee);
         return Map.of("message","Employee Details Updated Successfully",
                 "data",EmployeeMapper.mapToEmployeeDto(updatedEmployee));

    }
}
