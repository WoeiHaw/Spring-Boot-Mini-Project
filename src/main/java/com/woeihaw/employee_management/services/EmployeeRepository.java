package com.woeihaw.employee_management.services;

import com.woeihaw.employee_management.models.Department;
import com.woeihaw.employee_management.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee> findByName(String name);
    List<Employee> findByPosition(String position);

    List<Employee> findByDepartment(Department department);

}
