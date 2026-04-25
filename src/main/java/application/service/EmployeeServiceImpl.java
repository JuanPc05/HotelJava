package application.service;

import application.domain.Employee;
import application.service.outputs.EmployeeService;
import application.service.ports.EmployeeRepositoryPort;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepositoryPort employeeRepository;

    public EmployeeServiceImpl(EmployeeRepositoryPort employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {

        if (employeeRepository.findEmployeeById(employee.getId()).isPresent()) {
            throw new RuntimeException("Error: Ya existe un empleado con el ID: " + employee.getId());
        }


        return employeeRepository.createEmployee(employee);
    }

    @Override
    public Optional<Employee> findEmployeeById(int id) {
        // Simplemente delegamos al puerto de salida (Repositorio)
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        // Obtenemos todos los empleados del repositorio
        return employeeRepository.findAllEmployees();
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        if (employeeRepository.findEmployeeById(employee.getId()).isEmpty()) {
            throw new RuntimeException("Error: No se puede actualizar. El empleado no existe.");
        }

        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(int id) {

        if (employeeRepository.findEmployeeById(id).isPresent()) {
            employeeRepository.deleteEmployeeById(id);
        } else {
            System.out.println("Aviso: Intento de borrar ID inexistente (" + id + ").");
        }
    }
}