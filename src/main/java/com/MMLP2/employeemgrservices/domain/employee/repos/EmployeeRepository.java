package com.MMLP2.employeemgrservices.domain.employee.repos;

import com.MMLP2.employeemgrservices.domain.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
