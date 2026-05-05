package hotel.application;

import hotel.domain.Guest;
import hotel.application.inputs.GuestAdminService;
import hotel.application.ports.GuestAdminRepositoryPort;
import java.util.List;

public class GuestAdminServiceImpl implements GuestAdminService {

    private final GuestAdminRepositoryPort adminRepository;

    public GuestAdminServiceImpl(GuestAdminRepositoryPort adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Guest> getGuests() {
        return adminRepository.getAllGuests();
    }

    @Override
    public void deleteGuest(int id) {
        adminRepository.deleteGuestById(id);
    }
}