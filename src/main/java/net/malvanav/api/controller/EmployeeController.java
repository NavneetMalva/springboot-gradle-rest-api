package net.malvanav.api.controller;

import jakarta.validation.Valid;
import net.malvanav.api.entity.Employee;
import net.malvanav.api.exception.InvalidInputException;
import net.malvanav.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String welcome(){
        return "Welcome to Home Page of Gradle application.";
    }

    @PostMapping()
    public Employee saveEmployee(@Valid @RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @GetMapping("/emp")
    public Employee getEmployeeByParam(@RequestParam Long id){
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/employee")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@RequestParam Long id){

        // Retrieve the employee before deletion
        Employee deletedEmployee = employeeService.getEmployee(id);

        employeeService.deleteEmployee(id);

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("message","Employee deleted Successfully.");
        responseMap.put("deleted employee",deletedEmployee);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Handle invalid URL or other client-side issues
    @ExceptionHandler(InvalidInputException.class)
    public String handleInvalidInput(InvalidInputException ex) {
        return ex.getMessage();
    }


}
