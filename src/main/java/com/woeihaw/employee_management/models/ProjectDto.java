package com.woeihaw.employee_management.models;

import jakarta.validation.constraints.NotEmpty;

public class ProjectDto {

    private  int projectId;


    @NotEmpty(message = "Project name is required")
    private String projectName;

    private Employee employee;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
