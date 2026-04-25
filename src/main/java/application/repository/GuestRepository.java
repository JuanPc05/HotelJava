package application.repository;

import application.domain.Guest;
import application.service.ports.GuestRepositoryPort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestRepository implements GuestRepositoryPort {

    // 1. La lista ahora se recibe por constructor
    private final List<Guest> guests;

    public GuestRepository(List<Guest> sharedGuests) {
        this.guests = sharedGuests;
    }

    @Override
    public Guest createGuest(Guest guest) {
        guests.add(guest);
        return guest;
    }

    @Override
    public Guest updateGuest(Guest guest) {
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getId() == guest.getId()) {
                guests.set(i, guest);
                return guest;
            }
        }
        return null;
    }

    @Override
    public Optional<Guest> findGuestById(int id) {
        return guests.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }


}