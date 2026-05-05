package hotel.application.ports;

import hotel.domain.Guest;

import java.util.Optional;

public interface GuestRepositoryPort {
    Guest createGuest(Guest guest);
    Guest updateGuest(Guest guest);
    Optional<Guest> findGuestById(int id);

}
