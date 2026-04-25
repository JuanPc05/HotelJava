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
            System.out.println("1. Registrar Huésped (Usuario)");
            System.out.println("2. Actualizar mis datos (Usuario)");
            System.out.println("3. Listar todos los Huéspedes (Admin)");
            System.out.println("4. Eliminar Huésped por ID (Admin)"); // Nueva opción
            System.out.println("5. Volver al menú principal");

            int option = FormValidationUtil.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1 -> guestView.createGuestView();
                case 2 -> guestView.updateGuestView();
                case 3 -> guestView.displayAllGuests(); // Llama internamente al AdminService
                case 4 -> deleteGuestAction();         // Nueva acción administrativa
                case 5 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // Encapsulamos la acción de eliminar para mantener el switch limpio
    private void deleteGuestAction() {
        int id = FormValidationUtil.validateInt("Ingrese el ID del huésped a eliminar: ");
        guestView.deleteGuestById(id);
    }
}