package net.malvanav.api.service;

import net.malvanav.api.entity.Employee;
import net.malvanav.api.exception.EmployeeNotFoundException;
import net.malvanav.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Spring manages this class as a bean
public class EmployeeServiceImpl implements EmployeeService {

    /**
     Spring injects the implementation of an interface, not the interface itself.
     This allows Spring to manage the object lifecycle and provide the correct implementation at runtime.
     Spring automatically injects the EmployeeServiceImpl when it creates an instance of the controller
     because it knows that EmployeeServiceImpl implements EmployeeService.
    */
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee){
       return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id){
        return employeeRepository.findById(id)
                .orElseThrow( () -> new EmployeeNotFoundException("Employee not found with id : " + id));
    }

    public void deleteEmployee(Long id){
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id : " + id);
        }
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee employee){

        // fetch the existing employee
        Employee existingEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(()->new EmployeeNotFoundException("Employee not found with id : "+ employee.getId()));

        // Update the existing employee with the new data, only if provided (i.e., not null)
        if (employee.getName() != null) {
            existingEmployee.setName(employee.getName());
        }

        if (employee.getDepartment() != null) {
            existingEmployee.setDepartment(employee.getDepartment());
        }

        return employeeRepository.save(existingEmployee);
    }

}
