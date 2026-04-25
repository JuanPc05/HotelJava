package application.repository;

import application.domain.Guest;
import application.service.ports.GuestAdminRepositoryPort;
import java.util.List;

public class GuestAdminRepository implements GuestAdminRepositoryPort {

    // Importante: Este repositorio debe apuntar a la misma lista que el GuestRepository
    // En una DB real esto es automático, aquí en memoria, podrías pasar la lista por constructor
    private final List<Guest> sharedData;

    public GuestAdminRepository(List<Guest> sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public List<Guest> getAllGuests() {
        return sharedData;
    }

    @Override
    public void deleteGuestById(int id) {
        sharedData.removeIf(guest -> guest.getId() == id);
        System.out.println("Huésped con ID " + id + " eliminado por el Administrador.");
    }
}