package com.tac.ems_backend.controller;

import com.tac.ems_backend.dto.EmployeeDTO;
import com.tac.ems_backend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

     @PostMapping
    public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
          return new ResponseEntity<>(employeeService.createEmployee(employeeDTO),HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object>getAllEmployees() {
         try{
             return new ResponseEntity<>(employeeService.getEmployees(),HttpStatus.OK);
         }
         catch(Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
         }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Long id) {
     try {
         return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
     }
     catch(Exception e){
         return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
     }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeDTO employeeDTO){
         try{
             return new ResponseEntity<>(employeeService.updateEmployee(id,employeeDTO),HttpStatus.OK);
         }catch(Exception e){
             return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
