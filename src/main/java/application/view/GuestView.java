package application.view;

import application.domain.Guest;
import application.service.outputs.GuestAdminService;
import application.service.outputs.GuestService;
import application.util.FormValidationUtil;
import java.util.Optional;

public class GuestView {

    private final GuestService guestService;
    private final GuestAdminService guestAdminService;

    public GuestView(GuestService guestService, GuestAdminService guestAdminService) {
        this.guestService = guestService;
        this.guestAdminService = guestAdminService;
    }


    public void createGuestView() {
        System.out.println("\n--- REGISTRO DE NUEVO HUÉSPED ---");
        Guest newGuest = captureGuestData(null); // Pasamos null porque es nuevo
        guestService.createGuest(newGuest);
        System.out.println("¡Huésped creado exitosamente!");
    }

    public void updateGuestView() {
        System.out.println("\n--- ACTUALIZAR HUÉSPED ---");
        int id = FormValidationUtil.validateInt("Ingrese el ID del huésped a modificar: ");

        // Opcional: Validar si existe antes de pedir todos los datos
        Optional<Guest> existing = guestService.getGuestById(id);

        if (existing.isPresent()) {
            System.out.println("Huésped encontrado: " + existing.get().getName());
            Guest updatedGuest = captureGuestData(id); // Reutilizamos la captura pasando el ID fijo
            guestService.updateGuest(updatedGuest);
            System.out.println("¡Datos actualizados correctamente!");
        } else {
            System.out.println("Error: No se encontró un huésped con el ID " + id);
        }
    }

    public void displayAllGuests() {
        System.out.println("\n--- LISTADO DE HUÉSPEDES ---");
        guestAdminService.getGuests().forEach(System.out::println);
    }

    // Dentro de GuestView.java

    public void deleteGuestById(int id) {
        System.out.println("Solicitando eliminación al módulo administrativo...");
        // guestAdminService es el que tiene el permiso de borrar
        guestAdminService.deleteGuest(id);
        System.out.println("Proceso finalizado.");
    }

    // --- MÉTODOS PRIVADOS (REUTILIZACIÓN) ---

    /**
     * Este método centraliza la captura de datos por consola.
     * @param fixedId Si se pasa un ID, se usa ese (Update). Si es null, se pide al usuario (Create).
     */
    private Guest captureGuestData(Integer fixedId) {
        int id = (fixedId != null) ? fixedId : FormValidationUtil.validateInt("Ingrese el ID: ");

        String name = FormValidationUtil.validateString("Nombre: ");
        String lastName = FormValidationUtil.validateString("Apellido: ");
        String email = FormValidationUtil.validateString("Email: ");
        String password = FormValidationUtil.validateString("Password: ");
        boolean state = FormValidationUtil.validateBoolean("¿Activo? (true/false): ");
        String origin = FormValidationUtil.validateString("Origen: ");
        String guestType = FormValidationUtil.validateString("Tipo: ");

        return new Guest(id, name, lastName, email, password, state, origin, guestType);
    }
}