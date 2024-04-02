package com.woeihaw.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woeihaw.employee_management.models.Department;
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
