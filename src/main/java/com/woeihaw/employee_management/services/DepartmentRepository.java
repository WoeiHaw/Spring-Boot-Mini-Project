package com.woeihaw.employee_management.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woeihaw.employee_management.models.Department;
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
