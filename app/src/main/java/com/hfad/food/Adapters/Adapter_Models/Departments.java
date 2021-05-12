package com.hfad.food.Adapters.Adapter_Models;

public class Departments {
    public Integer image;
    public String departmentName;

    public Departments(Integer image, String departmentName) {
        this.image = image;
        this.departmentName = departmentName;
    }

    public Integer getImage() {
        return image;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
