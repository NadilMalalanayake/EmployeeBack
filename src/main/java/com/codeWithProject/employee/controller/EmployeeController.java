package com.codeWithProject.employee.controller;


import com.codeWithProject.employee.entity.Employee;
import com.codeWithProject.employee.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")        //annotate the controller pass data to DB (pass the data when user type in front end interface)
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee postemployee(@RequestBody Employee employee){
        return employeeService.postEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployee();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee with Id : " + id  +  "  deleted Successfully", HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){//get the employee id from the URL
        Employee employee=employeeService.getEmployeeById(id);
        if(employee==null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(employee);
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployees(@PathVariable Long id,@RequestBody Employee employee){
        Employee updateEmployee= employeeService.updateEmployee(id,employee);

        if(updateEmployee==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return ResponseEntity.ok(updateEmployee);
        }
    }
}
