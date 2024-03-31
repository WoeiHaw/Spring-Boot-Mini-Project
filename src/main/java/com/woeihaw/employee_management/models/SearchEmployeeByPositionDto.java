package com.woeihaw.employee_management.models;

import jakarta.validation.constraints.NotEmpty;

public class SearchEmployeeByPositionDto {
    @NotEmpty(message = "The position is required")
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
