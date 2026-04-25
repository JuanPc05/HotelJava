package application.service.ports;

import application.domain.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestRepositoryPort {
    Guest createGuest(Guest guest);
    Guest updateGuest(Guest guest);
    Optional<Guest> findGuestById(int id);

}
