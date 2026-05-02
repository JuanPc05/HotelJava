package application.userinterface;

import application.util.FormValidationUtil;
import application.view.BookingView;

public class MenuBooking {

    private final BookingView bookingView;

    public MenuBooking(BookingView bookingView) {
        this.bookingView = bookingView;
    }

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- GESTIÓN DE RESERVAS ---");
            System.out.println("1. Registrar Reserva");
            System.out.println("2. Actualizar Reserva");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Listar todas las Reservas");
            System.out.println("5. Eliminar Reserva");
            System.out.println("6. Volver al menú principal");

            int option = FormValidationUtil.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1 -> bookingView.createBooking();
                case 2 -> bookingView.updateBooking();
                case 3 -> bookingView.getBookingById();
                case 4 -> bookingView.getAllBookings();
                case 5 -> bookingView.deleteBookingById();
                case 6 -> exit = true;
                default -> System.out.println("❌ Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
}