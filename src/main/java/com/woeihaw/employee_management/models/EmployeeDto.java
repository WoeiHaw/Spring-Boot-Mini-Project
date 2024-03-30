package com.woeihaw.employee_management.models;

import jakarta.validation.constraints.NotEmpty;

public class EmployeeDto {
    private  int id;

    @NotEmpty(message = "The name is required")
    private String name;


    @NotEmpty(message = "Position is required")
    private String position;

//    @NotEmpty(message = "Position is required")
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
