package hotel.application.ports;

import hotel.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryPort {
    Employee createEmployee(Employee employee); //C
    Optional<Employee> findEmployeeById(int id); //R
    List<Employee> findAllEmployees(); //R
    Employee updateEmployee(Employee employee); //U
    void deleteEmployeeById(int id); //D
}
