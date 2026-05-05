package hotel.application.ports;

import hotel.domain.Guest;

import java.util.List;


public interface GuestAdminRepositoryPort {
    List<Guest> getAllGuests();
    void deleteGuestById(int id);
}
