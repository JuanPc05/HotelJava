package application.service.outputs;

import application.domain.BedRoom;

import java.util.List;
import java.util.Optional;

public interface BedRoomService {

    BedRoom createBedRoom(BedRoom bedRoom);
    BedRoom updateBedRoom(BedRoom bedRoom);
    Optional<BedRoom> getBedRoomById(int id);
    List<BedRoom> getAllBedRooms();
    void deleteBedRoomById(int id);

}
