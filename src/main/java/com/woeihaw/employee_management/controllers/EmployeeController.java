package com.woeihaw.employee_management.controllers;

import com.woeihaw.employee_management.models.Department;
import com.woeihaw.employee_management.models.Employee;
import com.woeihaw.employee_management.models.EmployeeDto;
import com.woeihaw.employee_management.service.DepartmentService;
import com.woeihaw.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private DepartmentService depService;
    @Autowired
    private EmployeeService empService ;
    @GetMapping({"","/"})
    public String showEmployeeList(Model model) throws InterruptedException {
        List<Employee> employees= empService.getAllEmployee();
        model.addAttribute("employees",employees);

        return "list/employee";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        EmployeeDto employeeDto = new EmployeeDto();
        List<Department> departments = depService.findAllDepartment();
        model.addAttribute("employeeDto",employeeDto);
        model.addAttribute("departments",departments);

        return "create/CreateEmployee";
    }

    @PostMapping("/create")
    public String createDepartment(
            @Valid @ModelAttribute EmployeeDto employeeDto,
            BindingResult result,
            Model model
    ){
        if (result.hasErrors()){

            List<Department> departments = depService.findAllDepartment();

            model.addAttribute("departments",departments);
            return "create/createEmployee";
        }
        Employee employee = new Employee();
        employee.setDepartment(employeeDto.getDepartment());

        employee.setName(employeeDto.getName());
        employee.setPosition(employeeDto.getPosition());
        empService.saveEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id){

        try{
            Employee employee = empService.findEmployeeById(id);
            List<Department> departments = depService.findAllDepartment();
            model.addAttribute("employee",employee);

            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setDepartment(employee.getDepartment());
            employeeDto.setPosition(employee.getPosition());
            model.addAttribute("employeeDto",employeeDto);
            model.addAttribute("departments",departments);

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/employee";
        }

        return "edit/EditEmployee";
    }
    @PostMapping("/edit")
    public String updateEmployee(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute EmployeeDto employeeDto,
            BindingResult result
    ){
        try{
            Employee employee =empService.findEmployeeById(id);
//            model.addAttribute("employee",employee);

            if(result.hasErrors()){
                List<Department> departments = depService.findAllDepartment();

                model.addAttribute("departments",departments);

                return "edit/EditEmployee";
            }
            employee.setName(employeeDto.getName());
            employee.setPosition(employeeDto.getPosition());
            employee.setDepartment(employeeDto.getDepartment());
            empService.saveEmployee(employee);

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(
            @RequestParam int id

    ){
        try{
            Employee employee = empService.findEmployeeById(id);
            employee.setDepartment(new Department());
            empService.deleteEmployee(employee);
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());

        }
        return "redirect:/employee";

    }



}
