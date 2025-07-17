package com.sme.smemanagementsystem.Service;


import com.sme.smemanagementsystem.Entity.Employee;
import com.sme.smemanagementsystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String name) {
        return employeeRepository.findById(name);
    }

    public Employee updateEmployee(String name, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(name);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDetails.getName());
            employee.setDesignation(employeeDetails.getDesignation());
            employee.setCtc(employeeDetails.getCtc());
            employee.setEmail(employeeDetails.getEmail());
            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }

    public void deleteEmployee(String name) {
        employeeRepository.deleteById(name);
    }

    public Optional<Employee> getEmployeeByname(String name) {
        return employeeRepository.findById(name);
    }
}
