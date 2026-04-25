package application.userinterface;

import application.util.FormValidationUtil;

public class MenuApp {

    private final MenuGuest menuGuest;
    private final MenuEmployee menuEmployee;
    private final MenuBedRoom menuBedRoom;

    public MenuApp(MenuGuest menuGuest, MenuEmployee menuEmployee, MenuBedRoom menuBedRoom) {
        this.menuGuest = menuGuest;
        this.menuEmployee = menuEmployee;
        this.menuBedRoom = menuBedRoom;
    }

    public void showMainMenu() {
        System.out.println("***********************************");
        System.out.println("* BIENVENIDO AL SISTEMA HOTEL   *");
        System.out.println("***********************************");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- PANEL DE CONTROL PRINCIPAL ---");
            System.out.println("1. Módulo de Huéspedes");
            System.out.println("2. Módulo de Empleados");
            System.out.println("3. Módulo de Habitaciones");
            System.out.println("0. Salir del Sistema");

            int option = FormValidationUtil.validateInt("Seleccione el módulo a gestionar: ");

            switch (option) {
                case 1 -> menuGuest.showMenu();
                case 2 -> menuEmployee.showMenu();
                case 3 -> menuBedRoom.showMenu();
                case 0 -> {
                    System.out.println("Finalizando aplicación... ¡Feliz día!");
                    exit = true;
                }
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }
}