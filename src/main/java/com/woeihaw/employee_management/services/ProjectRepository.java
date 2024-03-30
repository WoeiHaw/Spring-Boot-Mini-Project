package com.woeihaw.employee_management.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woeihaw.employee_management.models.Project;
public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
