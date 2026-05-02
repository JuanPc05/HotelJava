package application.service.outputs;

import application.domain.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    Optional<Booking> getBookingById(int id);
    List<Booking> getAllBookings();
    void deleteBookingById(int id);
}
