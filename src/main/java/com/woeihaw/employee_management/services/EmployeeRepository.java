package com.woeihaw.employee_management.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woeihaw.employee_management.models.Employee;
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
