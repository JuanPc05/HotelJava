package application.service.ports;

import application.domain.Guest;

import java.util.List;


public interface GuestAdminRepositoryPort {
    List<Guest> getAllGuests();
    void deleteGuestById(int id);
}
