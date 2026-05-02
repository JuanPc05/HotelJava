package application.view;

import application.domain.BedRoom;
import application.domain.Booking;
import application.domain.Guest;
import application.domain.enums.StatusBooking;
import application.service.outputs.BookingService;
import application.util.FormValidationUtil;

import java.time.LocalDate;
import java.util.Optional;

public class BookingView {

    private final BookingService bookingService;

    // Constructor con inyección de dependencias
    public BookingView(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // --- ACCIONES DEL MENÚ ---

    public void createBooking() {
        System.out.println("\n--- REGISTRAR RESERVA ---");
        try {
            // 1. Capturamos los datos (pasamos null porque el ID es nuevo)
            Booking newBooking = captureBookingData(null);

            // 2. Enviamos al servicio
            bookingService.createBooking(newBooking);

            System.out.println("Reserva creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear reserva: " + e.getMessage());
        }
    }

    public void updateBooking() {
        System.out.println("\n--- ACTUALIZAR RESERVA ---");
        int id = FormValidationUtil.validateInt("Ingrese el ID de la reserva a actualizar: ");

        Optional<Booking> existing = bookingService.getBookingById(id);

        if (existing.isPresent()) {
            System.out.println("\nDatos actuales de la reserva:");
            System.out.println(existing.get()); // Usa el Text Block de toString

            try {
                // Capturamos los nuevos datos manteniendo el mismo ID
                Booking updated = captureBookingData(id);
                bookingService.updateBooking(updated);
                System.out.println("Reserva actualizada correctamente.");
            } catch (Exception e) {
                System.out.println("Error al actualizar: " + e.getMessage());
            }

        } else {
            System.out.println("Error: Reserva no encontrada.");
        }
    }

    public void getBookingById() {
        int id = FormValidationUtil.validateInt("Ingrese el ID de la reserva a buscar: ");
        bookingService.getBookingById(id)
                .ifPresentOrElse(
                        // Llama al toString() que hiciste con Text Blocks
                        booking -> System.out.println(booking.toString()),
                        () -> System.out.println("No se encontró la reserva con ID " + id + ".")
                );
    }

    public void getAllBookings() {
        System.out.println("\n--- LISTADO DE RESERVAS ---");
        var list = bookingService.getAllBookings();
        if (list.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            // Imprime cada reserva usando su toString
            list.forEach(System.out::println);
        }
    }

    public void deleteBookingById() {
        int id = FormValidationUtil.validateInt("Ingrese el ID de la reserva a cancelar/eliminar: ");
        bookingService.deleteBookingById(id);
        System.out.println("Proceso completado.");
    }

    // --- MÉTODOS DE APOYO (REUTILIZACIÓN) ---

    /**
     * Captura los datos de consola para armar un objeto Booking provisional.
     * @param fixedId Si es null, pedimos un ID nuevo (o le ponemos 0 si es autoincremental).
     *                Si tiene valor, es una actualización.
     */
    private Booking captureBookingData(Integer fixedId) {
        // Si tienes base de datos real o auto-incremental, podrías omitir pedir el ID.
        // Por ahora lo pedimos si es null (creación).
        int id = (fixedId != null) ? fixedId : FormValidationUtil.validateInt("ID de la Reserva (0 para auto-generar): ");

        // 1. Capturamos referencias (Solo necesitamos los IDs para que el Service busque los objetos reales)
        int guestId = FormValidationUtil.validateInt("ID del Huésped (Debe existir previamente): ");
        Guest provisionalGuest = new Guest();
        provisionalGuest.setId(guestId); // Objeto "tonto" solo con el ID

        int roomId = FormValidationUtil.validateInt("ID de la Habitación (Debe estar disponible): ");
        BedRoom provisionalRoom = new BedRoom();
        provisionalRoom.setRoomId(roomId); // Objeto "tonto" solo con el ID

        // 2. Capturamos Fechas
        // Asumiendo que en FormValidationUtil tienes un validateDate()
        LocalDate checkIn = FormValidationUtil.validateDate("Fecha de Check-In (YYYY-MM-DD): ");
        LocalDate checkOut = FormValidationUtil.validateDate("Fecha de Check-Out (YYYY-MM-DD): ");

        // Validamos lógicamente la fecha en la vista (opcional, pero buena práctica)
        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("La fecha de Check-Out no puede ser anterior al Check-In.");
        }

        // 3. Estado de la reserva
        System.out.println("Estados disponibles:  1. EN_CURSO, 2. COMPLETADA, 3. CANCELADA");
        int statusOp = FormValidationUtil.validateInt("Seleccione el estado (1-3): ");
        StatusBooking status = switch (statusOp) {
            case 1 -> StatusBooking.IN_PROCESS;
            case 2 -> StatusBooking.COMPLETED;
            case 3 -> StatusBooking.CANCELLED;
            default -> StatusBooking.IN_PROCESS; // Default seguro
        };

        // 4. Instanciamos el objeto (El 'total' se calcula solo dentro del constructor de Booking como acordamos)
        return new Booking(id, checkIn, checkOut, status, provisionalGuest, provisionalRoom);
    }
}