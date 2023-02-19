package com.manage.employee.controller;

import com.manage.employee.model.Employee;
import com.manage.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String getAddEmployeeForm(Employee employee){
        return "add-employee";
    }

    @PostMapping("/add")
    public String insertEmployeeData(Employee employee,Model model){
        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }
}
