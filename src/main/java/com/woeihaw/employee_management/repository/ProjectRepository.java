package com.woeihaw.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woeihaw.employee_management.models.Project;
public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
