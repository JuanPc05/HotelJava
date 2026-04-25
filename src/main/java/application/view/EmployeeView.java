package application.view;

import application.domain.Employee;
import application.service.outputs.EmployeeService;
import application.util.FormValidationUtil;
import java.util.Optional;

public class EmployeeView {

    private final EmployeeService employeeService;

    public EmployeeView(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // --- ACCIONES DEL MENÚ ---

    public void createEmployeeView() {
        System.out.println("\n--- REGISTRO DE NUEVO EMPLEADO ---");
        try {
            Employee newEmployee = captureEmployeeData(null);
            employeeService.createEmployee(newEmployee);
            System.out.println("¡Empleado registrado con éxito!");
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void updateEmployeeView() {
        System.out.println("\n--- ACTUALIZAR EMPLEADO ---");
        int id = FormValidationUtil.validateInt("Ingrese el ID del empleado a modificar: ");

        Optional<Employee> existing = employeeService.findEmployeeById(id);

        if (existing.isPresent()) {
            System.out.println("Empleado encontrado: " + existing.get().getName());
            Employee updatedEmployee = captureEmployeeData(id);
            employeeService.updateEmployee(updatedEmployee);
            System.out.println("¡Empleado actualizado correctamente!");
        } else {
            System.out.println("Error: No se encontró un empleado con el ID " + id);
        }
    }

    public void displayAllEmployees() {
        System.out.println("\n--- LISTADO GENERAL DE EMPLEADOS ---");
        employeeService.findAllEmployees().forEach(System.out::println);
    }

    public void deleteEmployeeView() {
        System.out.println("\n--- ELIMINAR EMPLEADO ---");
        int id = FormValidationUtil.validateInt("Ingrese el ID del empleado a eliminar: ");
        employeeService.deleteEmployeeById(id);
        System.out.println("Operación de eliminación finalizada.");
    }

    // --- LÓGICA DE REUTILIZACIÓN (DRY) ---

    private Employee captureEmployeeData(Integer fixedId) {
        // Si fixedId es nulo, pedimos el ID (Create). Si no, usamos el fijo (Update).
        int id = (fixedId != null) ? fixedId : FormValidationUtil.validateInt("ID del Empleado: ");

        // Datos heredados de Person
        String name = FormValidationUtil.validateString("Nombre: ");
        String lastName = FormValidationUtil.validateString("Apellido: ");
        String email = FormValidationUtil.validateString("Email: ");
        String password = FormValidationUtil.validateString("Password: ");
        boolean state = FormValidationUtil.validateBoolean("¿Está activo? (true/false): ");

        // Datos específicos de Employee
        String position = FormValidationUtil.validateString("Cargo/Posición: ");
        double salary = FormValidationUtil.validateDouble("Salario mensual: ");

        // Retornamos el objeto construido (DDD: Constructor con todos los datos)
        return new Employee(id, name, lastName, email, password, state, position, salary);
    }
}