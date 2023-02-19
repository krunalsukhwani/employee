package com.manage.employee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Employee name is mandatory.")
    private String name;

    @NotBlank(message = "Employee address is mandatory.")
    private String address;

    @Min(value = 0,message = "Salary should be greater than zero.")
    private double salary;
}
