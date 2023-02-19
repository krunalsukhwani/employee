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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id")int id, Model model){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Employee ID" + id));
        employeeRepository.delete(employee);
        model.addAttribute("employees",employeeRepository.findAll());
        return "index";
    }

    @GetMapping("/update/{id}")
    public String moveToUpdateEmployeePage(@PathVariable("id")int id, Model model){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Employee ID" + id));
        model.addAttribute("employee",employee);
        return "update-employee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id")int id,Employee employee,Model model){
        employeeRepository.save(employee);
        model.addAttribute("employees",employeeRepository.findAll());
        return "index";
    }
}
