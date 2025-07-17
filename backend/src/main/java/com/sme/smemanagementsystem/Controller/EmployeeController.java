package com.sme.smemanagementsystem.Controller;


import com.sme.smemanagementsystem.Entity.Employee;
import com.sme.smemanagementsystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String name) {
        Optional<Employee> employee = employeeService.getEmployeeById(name);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String name, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(name, employeeDetails);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String name) {
        employeeService.deleteEmployee(name);
        return ResponseEntity.noContent().build();
    }
    
}
