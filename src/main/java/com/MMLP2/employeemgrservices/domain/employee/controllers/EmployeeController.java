package com.MMLP2.employeemgrservices.domain.employee.controllers;

import com.MMLP2.employeemgrservices.domain.employee.models.Employee;
import com.MMLP2.employeemgrservices.domain.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        List<Employee> employees = employeeService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        employee = employeeService.create(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("{Id}")
    public ResponseEntity<Employee> getById(@PathVariable("Id") Long Id){
        Employee employee = employeeService.getById(Id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("lookup")
    public ResponseEntity<Employee> getByEmail(@RequestParam String email){
        Employee employee = employeeService.getByEmail(email);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PutMapping("{Id}")
    public ResponseEntity<Employee> update(@PathVariable("Id") Long Id, @RequestBody Employee employeeDetail){
        employeeDetail = employeeService.update(Id, employeeDetail);
        return new ResponseEntity<>(employeeDetail, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{Id}")
    public ResponseEntity delete(@PathVariable("Id") Long Id){
        employeeService.delete(Id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
