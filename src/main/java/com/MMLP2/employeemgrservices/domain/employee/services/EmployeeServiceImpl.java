package com.MMLP2.employeemgrservices.domain.employee.services;

import com.MMLP2.employeemgrservices.domain.core.exceptions.ResourceCreationException;
import com.MMLP2.employeemgrservices.domain.core.exceptions.ResourceNotFoundException;
import com.MMLP2.employeemgrservices.domain.employee.models.Employee;
import com.MMLP2.employeemgrservices.domain.employee.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee create(Employee employee) throws ResourceCreationException {
        Optional<Employee> optional = employeeRepository.findByEmail(employee.getEmail());
        if(optional.isPresent())
            throw new ResourceCreationException("Employee with Email exists: " + employee.getEmail());
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee getById(Long Id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee found with this ID: " + Id));
        return employee;
    }

    @Override
    public Employee getByEmail(String email) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee with email: " + email));
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Long Id, Employee employeeDetail) throws ResourceNotFoundException {
        Employee employee = getById(Id);
        employee.setFirstName(employeeDetail.getFirstName());
        employee.setLastName(employeeDetail.getLastName());
        employee.setEmail(employeeDetail.getEmail());
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(Long Id) {
        Employee employee = getById(Id);
        employeeRepository.delete(employee);
    }
}
