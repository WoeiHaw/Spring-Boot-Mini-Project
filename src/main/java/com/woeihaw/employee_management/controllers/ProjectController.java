package com.woeihaw.employee_management.controllers;

import com.woeihaw.employee_management.models.Employee;
import com.woeihaw.employee_management.models.Project;
import com.woeihaw.employee_management.models.ProjectDto;
import com.woeihaw.employee_management.service.EmployeeService;
import com.woeihaw.employee_management.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService proService;

    @Autowired
    EmployeeService empService;
    @GetMapping({"","/"})
    public String showProject(Model model){
        List<Project> projects =proService.findAllProject();
        model.addAttribute("projects",projects);
        return "list/project";
    }
    @GetMapping("/create")
    public String showCreateProject(Model model){
        ProjectDto projectDto = new ProjectDto();
        List<Employee> employees = empService.getAllEmployee();
        model.addAttribute("projectDto",projectDto);
        model.addAttribute("employees",employees);
        return "create/CreateProject";
    }

    @PostMapping("/create")
    public String createProject(
            @Valid @ModelAttribute ProjectDto projectDto,
            BindingResult result,
            Model model
    ){

        if(result.hasErrors()){
            List<Employee> employees =empService.getAllEmployee();
            model.addAttribute("employees",employees);
            return "create/CreateProject";
        }
        Project project = new Project();
        project.setEmployee(projectDto.getEmployee());
        project.setProjectName(projectDto.getProjectName());
        proService.saveProject(project);
        return "redirect:/project";
    }

    @GetMapping("/edit")
    public String showCreateProject(
            Model model,
            @RequestParam int id
    ){
        try{
            Project project = proService.findProjectById(id);


            ProjectDto projectDto = new ProjectDto();
            projectDto.setProjectId(project.getProjectId());
            projectDto.setProjectName(project.getProjectName());
            projectDto.setEmployee(project.getEmployee());

            List<Employee> employees =empService.getAllEmployee();
            model.addAttribute("employees",employees);
            model.addAttribute("projectDto",projectDto);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/project";
        }
        return "edit/EditProject";
    }

    @PostMapping("/edit")
    public String editProject(
            @Valid @ModelAttribute ProjectDto projectDto,
            BindingResult result,
            Model model,
            @RequestParam int id

    ){
        try{
            Project project = proService.findProjectById(id);
            if(result.hasErrors()){

                List<Employee> employees = empService.getAllEmployee();
                model.addAttribute("employees",employees);
                projectDto.setProjectId(project.getProjectId());
                return "edit/EditProject";
            }
            project.setProjectName(projectDto.getProjectName());
            project.setEmployee(projectDto.getEmployee());

           proService.saveProject(project);

        }catch (Exception e){
            System.out.println("Exception : " + e.getMessage());
        }
        return "redirect:/project";
    }

    @GetMapping("/delete")
    public String deleteProject(
            @RequestParam int id
    ){
        try {
            Project project = proService.findProjectById(id);
            project.setEmployee(new Employee());
            proService.deleteProject(project);
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return "redirect:/project";
    }
}
