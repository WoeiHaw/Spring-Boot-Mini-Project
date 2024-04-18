package com.woeihaw.employee_management.controllers;

import com.woeihaw.employee_management.models.*;
import com.woeihaw.employee_management.service.DepartmentService;
import com.woeihaw.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/search")
public class SearchEmployeeController {

    @Autowired
    private DepartmentService depService;
    @Autowired
    private EmployeeService empService ;
    @GetMapping({"","/"})
    public String searchEmployee(){
        return "/search/index";
    }
    @GetMapping("/id")
    public String showEmployeeByIDPage(Model model){
        SearchEmployeeByIdDto searchEmployeeByIdDto = new SearchEmployeeByIdDto();
        model.addAttribute("searchEmployeeByIdDto",searchEmployeeByIdDto);
        return "/search/id";
    }
    @PostMapping("/id")
    public String searchEmployeeById(
            @Valid @ModelAttribute SearchEmployeeByIdDto searchEmployeeByIdDto,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            return "search/id";
        }
        try{

            Employee employee = empService.findEmployeeById(searchEmployeeByIdDto.getId());
            model.addAttribute("employee",employee);

            return "search/ShowSearchEmployeeById";
        }catch (NoSuchElementException e){
            String message = "ID no exist";
            System.out.println(e.getMessage());
            model.addAttribute("message", message);
            return "/search/id";

        }
    }

    @GetMapping("/name")
    public String showSearchByNamePage(Model model)
    {
        SearchEmployeeByNameDto searchEmployeeByNameDto = new SearchEmployeeByNameDto();
        model.addAttribute("searchEmployeeByNameDto",searchEmployeeByNameDto);
        return "/search/name";
    }
    @PostMapping("/name")
    public String showEmployeeNameResult(
            @Valid @ModelAttribute SearchEmployeeByNameDto searchEmployeeByNameDto,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            return "search/name";
        }
        try{

            List<Employee> employees = empService.findEmployeeByName(searchEmployeeByNameDto.getName());
            model.addAttribute("employees",employees);
            if (employees.size() != 0){
                return "search/ShowSearchEmployee";
            }else {
                String message = "Employee no found";
                model.addAttribute("message",message);
                return "search/name";
            }

        }catch (NoSuchElementException e){

            System.out.println(e.getMessage());


        }
        return "/search/name";
    }

    @GetMapping("/position")
    public String showSearchEmployeeByPositionPage(Model model){
        SearchEmployeeByPositionDto searchEmployeeByPositionDto = new SearchEmployeeByPositionDto();
        model.addAttribute("searchEmployeeByPositionDto",searchEmployeeByPositionDto);
        return"/search/position";
    }

    @PostMapping("/position")
    public String showSearchEmployeeByPositionResult(
            @Valid @ModelAttribute SearchEmployeeByPositionDto searchEmployeeByPositionDto,
            BindingResult result,
            Model model
    ){
        if (result.hasErrors()){
            return "/search/position";

        }
        try{
            List<Employee> employees =  empService.findEmployeeByPosition(searchEmployeeByPositionDto.getPosition());
            model.addAttribute("employees",employees);
            if (employees.size()!=0){
                return "search/ShowSearchEmployee";
            }else{
                String message = "No employee belong to this position";
                model.addAttribute("message",message);
                return "/search/position";

            }
        }catch (NoSuchElementException e){

            System.out.println(e.getMessage());


        }
        return "/search/position";
    }
    @GetMapping("/department")
    public String showDepartmentSearchPage(Model model){
        List<Department> departments = depService.findAllDepartment();
        SearchEmployeeByDepartmentDto searchEmployeeByDepartmentDto = new SearchEmployeeByDepartmentDto();
        model.addAttribute("departments",departments);
        model.addAttribute("searchEmployeeByDepartmentDto",searchEmployeeByDepartmentDto);
        return "/search/department";
    }

    @PostMapping("/department")
    public  String showDepartmentSearchResult(
            @Valid @ModelAttribute SearchEmployeeByDepartmentDto searchEmployeeByDepartmentDto,
            BindingResult result,
            Model model
    ){
        if (result.hasErrors()){
            return "/search/department";

        }
        try{
            List<Employee> employees = empService.findEmployeeByDepartment(searchEmployeeByDepartmentDto.getDepartment());
            model.addAttribute("employees",employees);
            if (employees.size()!=0){
                return "search/ShowSearchEmployee";
            }else{
                String message = "No employee belong to this department";
                List<Department> departments = depService.findAllDepartment();
                model.addAttribute("message",message);
                model.addAttribute("departments",departments);
                return "/search/department";

            }
        }catch (NoSuchElementException e){

            System.out.println(e.getMessage());


        }
        List<Department> departments = depService.findAllDepartment();

        model.addAttribute("departments",departments);
        return "/search/department";

    }
}
