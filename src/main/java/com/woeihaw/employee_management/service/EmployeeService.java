package com.woeihaw.employee_management.service;

import com.woeihaw.employee_management.models.Department;
import com.woeihaw.employee_management.models.Employee;
import com.woeihaw.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;
    @Cacheable("employee")
    public List<Employee> getAllEmployee()  {
        System.out.println("Loading All Employee");
        return repo.findAll();
    }
    @CacheEvict(value = "employee",allEntries = true)
    public void saveEmployee(Employee employee){
        repo.save(employee);
    }
    @Cacheable("employee")
    public Employee findEmployeeById(int id){
        return repo.findById(id).get();
    }
    @CacheEvict(value = "employee",allEntries = true)
    public void deleteEmployee(Employee employee){
        repo.delete(employee);
    }

    @Cacheable("employee")
    public List<Employee> findEmployeeByName(String name){
        return repo.findByName(name);
    }

    @Cacheable("employee")
    public List<Employee> findEmployeeByPosition(String position){
        return repo.findByPosition(position);
    }

    @Cacheable({"employee","department"})
    public List<Employee> findEmployeeByDepartment(Department department){
        return repo.findByDepartment(department);
    }
}
