package application.service;

import application.domain.BedRoom;
import application.domain.Booking;
import application.domain.Guest;
import application.service.outputs.BookingService;
import application.service.ports.BedRoomRepositoryPort;
import application.service.ports.BookingRepositoryPort;
import application.service.ports.GuestRepositoryPort;

import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {

    private final BookingRepositoryPort bookingRepositoryPort;
    private final GuestRepositoryPort guestRepositoryPort;
    private final BedRoomRepositoryPort bedRoomRepositoryPort;

    // Inyección de dependencias
    public BookingServiceImpl(BookingRepositoryPort bookingRepositoryPort,
                              GuestRepositoryPort guestRepositoryPort,
                              BedRoomRepositoryPort bedRoomRepositoryPort) {
        this.bookingRepositoryPort = bookingRepositoryPort;
        this.guestRepositoryPort = guestRepositoryPort;
        this.bedRoomRepositoryPort = bedRoomRepositoryPort;
    }

    @Override
    public Booking createBooking(Booking booking) {
        // 1. Validar y traer el objeto real del huésped
        Guest officialGuest = findOfficialGuest(booking.getGuest().getId());
        booking.setGuest(officialGuest);

        // 2. Validar y traer el objeto real de la habitación
        BedRoom officialRoom = findOfficialRoom(booking.getBedRoom().getRoomId());

        // 3. Regla de negocio crucial: ¿La habitación está disponible?
        if (!officialRoom.getState().equalsIgnoreCase("DISPONIBLE")) {
            throw new IllegalStateException("La habitación " + officialRoom.getRoom() + " no se encuentra disponible.");
        }

        // Asignamos la habitación real a la reserva
        booking.setBedRoom(officialRoom);

        officialRoom.setState("OCUPADA");
        bedRoomRepositoryPort.updateBedRoom(officialRoom);

        // 4. Guardar en el repositorio
        return bookingRepositoryPort.saveBooking(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        // 1. Verificar existencia
        if (bookingRepositoryPort.findBookingById(booking.getId()).isEmpty()) {
            throw new IllegalArgumentException("No se puede actualizar: la reserva no existe.");
        }

        // 2. Validar que las dependencias actualizadas sigan siendo válidas
        Guest officialGuest = findOfficialGuest(booking.getGuest().getId());
        booking.setGuest(officialGuest);

        BedRoom officialRoom = findOfficialRoom(booking.getBedRoom().getRoomId());
        booking.setBedRoom(officialRoom);

        // 3. Actualizar en persistencia
        return bookingRepositoryPort.updateBooking(booking);
    }

    @Override
    public Optional<Booking> getBookingById(int id) {
        return bookingRepositoryPort.findBookingById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepositoryPort.findAllBookings();
    }

    @Override
    public void deleteBookingById(int id) {
        bookingRepositoryPort.deleteBookingById(id);
    }

    // --- Métodos privados auxiliares ---

    private Guest findOfficialGuest(int guestId) {
        return guestRepositoryPort.findGuestById(guestId)
                .orElseThrow(() -> new IllegalArgumentException("ID de huésped no válido: " + guestId));
    }

    private BedRoom findOfficialRoom(int roomId) {
        return bedRoomRepositoryPort.findBedRoomById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("ID de habitación no válido: " + roomId));
    }
}