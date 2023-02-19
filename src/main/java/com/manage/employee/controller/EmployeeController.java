package com.manage.employee.controller;

import com.manage.employee.model.Employee;
import com.manage.employee.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String insertEmployeeData(@Valid Employee employee, BindingResult bindingResult, Model model){

        //custom validation
        if(employee.getName().startsWith("D")){
            bindingResult.addError(new FieldError("employee","name","Employee name should not starts with D"));
        }

        if(bindingResult.hasErrors()){
            return "add-employee";
        }

        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }
}
