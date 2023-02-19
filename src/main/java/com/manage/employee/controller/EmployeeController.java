package com.manage.employee.controller;

import com.manage.employee.model.Employee;
import com.manage.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/add")
    public String getAddEmployeeForm(Employee employee){
        return "add-employee";
    }

    @PostMapping("/add")
    public String insertEmployeeData(Employee employee){
        employeeRepository.save(employee);
        return "success";
    }
}
