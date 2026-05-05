package hotel.application.inputs;

import hotel.domain.Guest;

import java.util.List;

public interface GuestAdminService {

    public List<Guest> getGuests();
    public void deleteGuest(int id);

}
