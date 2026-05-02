package application.repository;

import application.domain.BedRoom;
import application.domain.BedRoomType;
import application.domain.Booking;
import application.domain.Guest;
import application.domain.enums.StatusBooking;
import application.service.ports.BookingRepositoryPort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookingRepository implements BookingRepositoryPort {

    // Usamos la Interfaz Map y la implementación HashMap.
    // La llave (Integer) será el ID de la reserva, el Valor será el objeto Booking.
    private final Map<Integer, Booking> bookings = new HashMap<>();

    // Un contador para simular el auto-incremento de la base de datos
    private int currentId = 1;

    public BookingRepository() {
        // --- DATOS GUARDADOS PARA PRUEBAS (Se ejecutan al instanciar el repo) ---

        /*IN_PROCESS,
    COMPLETED,
    CANCELLED*/

        // 1. Creamos dependencias falsas (Mock)
        Guest guest1 = new Guest(1, "Ana", "López", "ana@mail.com", "pass", true, "Colombia", "VIP");
        BedRoom room1 = new BedRoom(1, "201", new BedRoomType(1, "Individual"), 120000.0, "RESERVADA");

        Guest guest2 = new Guest(2, "Carlos", "Serna", "carlos@mail.com", "pass", true, "México", "Regular");
        BedRoom room2 = new BedRoom(2, "202", new BedRoomType(2, "Doble"), 180000.0, "OCUPADA");

        // 2. Creamos las reservas y usamos currentId++ para asignarles el ID
        Booking booking1 = new Booking(currentId++, LocalDate.now(), LocalDate.now().plusDays(3), StatusBooking.COMPLETED, guest1, room1);
        Booking booking2 = new Booking(currentId++, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), StatusBooking.IN_PROCESS, guest2, room2);

        // 3. Las guardamos en el HashMap
        bookings.put(booking1.getId(), booking1);
        bookings.put(booking2.getId(), booking2);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        // Si la reserva es nueva (id 0), le asignamos el siguiente ID disponible
        if (booking.getId() == 0) {
            booking.setId(currentId++);
        }
        bookings.put(booking.getId(), booking);
        return booking;
    }

    @Override
    public Booking updateBooking(Booking booking) {
        // Validamos si la reserva existe antes de actualizar
        if (!bookings.containsKey(booking.getId())) {
            throw new IllegalArgumentException("Reserva con Id " + booking.getId() + " no encontrada.");
        }

        // En un HashMap, usar put() con una llave existente sobreescribe el valor antiguo
        bookings.put(booking.getId(), booking);
        return booking;
    }

    @Override
    public Optional<Booking> findBookingById(int id) {
        // Optional.ofNullable maneja automáticamente si el .get() devuelve null
        return Optional.ofNullable(bookings.get(id));
    }

    @Override
    public List<Booking> findAllBookings() {
        // Extraemos solo los valores (las reservas) y las devolvemos como una Lista
        return new ArrayList<>(bookings.values());
    }

    @Override
    public void deleteBookingById(int id) {
        // El método remove de HashMap es seguro y rápido
        Booking removed = bookings.remove(id);

        if (removed != null) {
            System.out.println("Reserva con id " + id + " ha sido eliminada.");
        } else {
            System.out.println("Reserva con id " + id + " no encontrada.");
        }
    }
}