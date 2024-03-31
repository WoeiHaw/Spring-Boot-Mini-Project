package com.woeihaw.employee_management.models;

import jakarta.validation.constraints.NotEmpty;

public class SearchEmployeeByNameDto {
    @NotEmpty(message = "The name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
