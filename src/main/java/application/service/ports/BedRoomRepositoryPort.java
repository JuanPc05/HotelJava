package application.service.ports;

import application.domain.BedRoom;

import java.util.List;
import java.util.Optional;

public interface BedRoomRepositoryPort {

    BedRoom saveBedRoom(BedRoom bedRoom);
    BedRoom updateBedRoom(BedRoom bedRoom);
    Optional<BedRoom> findBedRoomById(int id);
    List<BedRoom> findAllBedRooms();
    void deleteBedRoomById(int id);

}
