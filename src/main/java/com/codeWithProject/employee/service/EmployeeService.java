package com.codeWithProject.employee.service;


import com.codeWithProject.employee.Repository.EmployeeRepository;
import com.codeWithProject.employee.entity.Employee;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // inject employee repo
public class EmployeeService {

    public final EmployeeRepository employeeRepository;

    public Employee postEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee with ID :" + id  + " not found");
        }

        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);  //this will return one optional employee
    }

    public Employee updateEmployee(Long id,Employee employee){
        Optional<Employee> optionalEmployee=employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee existingEmployee=optionalEmployee.get();

            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(existingEmployee);

        }else {
            return null;
        }

    }

}
