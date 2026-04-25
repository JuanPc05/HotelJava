package application.repository;


import application.domain.Guest;
import application.service.ports.GuestRepositoryPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GuestRepository implements GuestRepositoryPort {


    List<Guest> guests = new ArrayList<>(
            Arrays.asList(
                   new Guest(1, "Maria", "Gomez","mg@mail.com", "123456" , true , "Medellín" , "Nuevo"),
                   new Guest(2, "Juan", "Perez", "juan@mail.com","12345678", true , "Bogotá", "Frecuente")
            )
    );


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

    public List<Guest> getAllGuests(){

        for(Guest guest : guests){
            System.out.println(guest);
        }

        return guests;

    }

    @Override
    public void deleteGuestById(int id) {
        guests.removeIf(g -> g.getId() == id);
    }


}
