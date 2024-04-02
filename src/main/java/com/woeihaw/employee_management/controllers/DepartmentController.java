package com.woeihaw.employee_management.controllers;

import com.woeihaw.employee_management.models.Department;
import com.woeihaw.employee_management.models.DepartmentDto;
import com.woeihaw.employee_management.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping({"","/"})
    public String showDepartment(Model model){
        List<Department> departments =service.findAllDepartment();
        model.addAttribute("departments",departments);
        return "list/department";

    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        DepartmentDto departmentDto = new DepartmentDto();
        model.addAttribute("departmentDto",departmentDto);
        return "create/CreateDepartment";
    }

    @PostMapping("/create")
    public String createDepartment(
            @Valid @ModelAttribute DepartmentDto departmentDto,
            BindingResult result
    ){
       if(result.hasErrors()){
           return "create/CreateDepartment";
       }
       Department department = new Department();
       department.setDepartmentName(departmentDto.getDepartmentName());
       service.saveDepartment(department);
       return "redirect:/department";

    }
    @GetMapping("/edit")
    public String editDepartment(Model model, @RequestParam int id){
        try{

            Department department = service.findDepartmentById(id);
            model.addAttribute("department",department);

            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setDepartmentId(department.getDepartmentId());
            departmentDto.setDepartmentName(department.getDepartmentName());

            model.addAttribute("departmentDto",departmentDto);

        }catch(Exception e){
            System.out.println("Exception: " +e.getMessage());
            return "redirect:/department";
        }

        return "edit/EditDepartment";
    }

    @PostMapping("/edit")
    public String updateDepartment(
            @Valid @ModelAttribute DepartmentDto departmentDto,
            BindingResult result,
            @RequestParam int id

    ){
        try{
            Department department = service.findDepartmentById(id);
//            model.addAttribute("department",department);
            if(result.hasErrors()){
                departmentDto.setDepartmentId(department.getDepartmentId());
                return "edit/EditDepartment";
            }
            department.setDepartmentName(departmentDto.getDepartmentName());
            service.saveDepartment(department);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/department";
    }

    @GetMapping("/delete")
    public String deleteDepartment(
            @RequestParam int id
    ){
        try{
            Department department = service.findDepartmentById(id);
            service.deleteDepartment(department);

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return "redirect:/department";
    }

}
