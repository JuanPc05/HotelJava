package application.service.ports;

import application.domain.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepositoryPort {
    Booking saveBooking(Booking booking);
    Booking updateBooking(Booking booking);
    Optional<Booking> findBookingById(int id);
    List<Booking> findAllBookings();
    void deleteBookingById(int id);
}
