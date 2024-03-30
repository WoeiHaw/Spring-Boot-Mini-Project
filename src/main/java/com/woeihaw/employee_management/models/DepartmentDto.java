package com.woeihaw.employee_management.models;

import jakarta.validation.constraints.NotEmpty;

public class DepartmentDto {

    private int departmentId;

    @NotEmpty(message = "The department name is required")
    private String departmentName;


    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
