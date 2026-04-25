package application.service;

import application.domain.Guest;
import application.repository.GuestRepository; // Ojo aquí, mejor usa la Interfaz (Port)
import application.service.outputs.GuestService;
import application.service.ports.GuestRepositoryPort;

import java.util.Optional;

public class GuestServiceImpl implements GuestService {

    // 1. Inyectamos la Interfaz (Puerto), no la implementación directa
    private final GuestRepositoryPort guestRepository;

    public GuestServiceImpl(GuestRepositoryPort guestRepository){
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest createGuest(Guest guest) {

        if (guest.getEmail() == null || !guest.getEmail().contains("@")) {
            throw new IllegalArgumentException("El formato del email es inválido.");
        }

        Optional<Guest> existing = guestRepository.findGuestById(guest.getId());
        if (existing.isPresent()) {
            throw new RuntimeException("Ya existe un huésped con el ID: " + guest.getId());
        }

        return guestRepository.createGuest(guest);
    }

    @Override
    public Guest updateGuest(Guest guest) {

        if (guestRepository.findGuestById(guest.getId()).isPresent()) {
            return guestRepository.updateGuest(guest);
        }
        return null;
    }

    @Override
    public Optional<Guest> getGuestById(int id) {
        return guestRepository.findGuestById(id);
    }
}