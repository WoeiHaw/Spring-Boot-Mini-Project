package com.woeihaw.employee_management.service;

import com.woeihaw.employee_management.models.Project;
import com.woeihaw.employee_management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository repo;

    @Cacheable("project")
    public List<Project> findAllProject(){
        System.out.println("Loading All Project");
        return repo.findAll();
    }

    @CacheEvict(value = "project",allEntries = true)
    public void saveProject(Project project){
        repo.save(project);
    }

    @Cacheable("project")
    public Project findProjectById(int id){
        return repo.findById(id).get();
    }

    @CacheEvict(value = "project",allEntries = true)
    public  void deleteProject(Project project){
        repo.delete(project);
    }

}
