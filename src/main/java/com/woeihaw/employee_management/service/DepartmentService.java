package com.woeihaw.employee_management.service;

import com.woeihaw.employee_management.models.Department;
import com.woeihaw.employee_management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository repo;

    @Cacheable("department")
    public List<Department> findAllDepartment(){
        System.out.println("Loading All department");
        return repo.findAll();
    }
    @CacheEvict(value = "department",allEntries = true)
    public void saveDepartment(Department department){
        repo.save(department);
    }

    @Cacheable("department")
    public Department findDepartmentById(int id){
        return repo.findById(id).get();
    }

    @CacheEvict(value = "department",allEntries = true)
    public void deleteDepartment(Department department){
        repo.delete(department);
    }

}
