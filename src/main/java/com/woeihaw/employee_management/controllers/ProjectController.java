package com.woeihaw.employee_management.controllers;

import com.woeihaw.employee_management.models.Employee;
import com.woeihaw.employee_management.models.Project;
import com.woeihaw.employee_management.models.ProjectDto;
import com.woeihaw.employee_management.services.EmployeeRepository;
import com.woeihaw.employee_management.services.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private EmployeeRepository empRepo;
    @GetMapping({"","/"})
    public String showProject(Model model){
        List<Project> projects =projectRepo.findAll();
        model.addAttribute("projects",projects);
        return "list/project";
    }
    @GetMapping("/create")
    public String showCreateProject(Model model){
        ProjectDto projectDto = new ProjectDto();
        List<Employee> employees = empRepo.findAll();
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
            List<Employee> employees = empRepo.findAll();
            model.addAttribute("employees",employees);
            return "create/CreateProject";
        }
        Project project = new Project();
        project.setEmployee(projectDto.getEmployee());
        project.setProjectName(projectDto.getProjectName());
        projectRepo.save(project);
        return "redirect:/project";
    }

    @GetMapping("/edit")
    public String showCreateProject(
            Model model,
            @RequestParam int id
    ){
        try{
            Project project = projectRepo.findById(id).get();


            ProjectDto projectDto = new ProjectDto();
            projectDto.setProjectId(project.getProjectId());
            projectDto.setProjectName(project.getProjectName());
            projectDto.setEmployee(project.getEmployee());

            List<Employee> employees = empRepo.findAll();
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
            Project project = projectRepo.findById(id).get();
            System.out.println(projectDto.getProjectId());
            System.out.println(projectDto.getProjectName());
            System.out.println(projectDto.getEmployee().getName());
            if(result.hasErrors()){

                List<Employee> employees = empRepo.findAll();
                model.addAttribute("employees",employees);
                projectDto.setProjectId(project.getProjectId());
                return "edit/EditProject";
            }
            project.setProjectName(projectDto.getProjectName());
            project.setEmployee(projectDto.getEmployee());

            projectRepo.save(project);

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
            Project project = projectRepo.findById(id).get();
            project.setEmployee(new Employee());
            projectRepo.delete(project);
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return "redirect:/project";
    }
}
