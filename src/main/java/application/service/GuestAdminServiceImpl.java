package application.service;

import application.domain.Guest;
import application.service.outputs.GuestAdminService;
import application.service.ports.GuestAdminRepositoryPort;
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