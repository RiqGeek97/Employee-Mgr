package com.MMLP2.employeemgrservices.domain.employee.services;

import com.MMLP2.employeemgrservices.domain.core.exceptions.ResourceCreationException;
import com.MMLP2.employeemgrservices.domain.core.exceptions.ResourceNotFoundException;
import com.MMLP2.employeemgrservices.domain.employee.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee) throws ResourceCreationException;
    Employee getById(Long Id) throws ResourceNotFoundException;
    Employee getByEmail(String email) throws ResourceNotFoundException;
    List<Employee> getAll();
    Employee update(Long Id, Employee employeeDetail) throws ResourceNotFoundException;
    void delete(Long Id);
}
