package application.repository;

import application.domain.Employee;
import application.service.ports.EmployeeRepositoryPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements EmployeeRepositoryPort {

    List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "Mery", "Cris", "mc@gmail.com", "65412", true, "Recepcionista", 1500),
                    new Employee(2, "Fabio", "Gomez", "fg@gmail.com", "541230", true, "Servicio", 1200)
            )
    );

    @Override
    public Employee createEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public Optional<Employee> findEmployeeById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employees.set(i, employee);
                return employee;
            }
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(int id) {
        employees.removeIf(e -> e.getId() == id);
    }
}