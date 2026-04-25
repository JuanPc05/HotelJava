package application.userinterface;

import application.util.FormValidationUtil;
import application.view.EmployeeView;

public class MenuEmployee {
    private final EmployeeView employeeView;

    public MenuEmployee(EmployeeView employeeView) {
        this.employeeView = employeeView;
    }

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Actualizar Empleado");
            System.out.println("3. Listar Empleados");
            System.out.println("4. Eliminar Empleado");
            System.out.println("5. Volver al menú principal");

            int option = FormValidationUtil.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1 -> employeeView.createEmployeeView();
                case 2 -> employeeView.updateEmployeeView();
                case 3 -> employeeView.displayAllEmployees();
                case 4 -> employeeView.deleteEmployeeView();
                case 5 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}