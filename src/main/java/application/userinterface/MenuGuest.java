package application.userinterface;

import application.util.FormValidationUtil;
import application.view.GuestView;

public class MenuGuest {
    private final GuestView guestView;

    public MenuGuest(GuestView guestView) {
        this.guestView = guestView;
    }

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- GESTIÓN DE HUÉSPEDES ---");
            System.out.println("1. Registrar Huésped");
            System.out.println("2. Actualizar Huésped");
            System.out.println("3. Listar todos los Huéspedes");
            System.out.println("4. Volver al menú principal");

            int option = FormValidationUtil.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1 -> guestView.createGuestView();
                case 2 -> guestView.updateGuestView();
                case 3 -> guestView.displayAllGuests();
                case 4 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}