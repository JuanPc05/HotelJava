package application.service.outputs;

import application.domain.BedRoom;

import java.util.List;
import java.util.Optional;

public interface BedRoomService {

    BedRoom createBedRoom(int roomId,String room,int typeId,double  price,String state);
    BedRoom updateBedRoom(int id, String room, int typeId, double price, String state);
    Optional<BedRoom> getBedRoomById(int id);
    List<BedRoom> getAllBedRooms();
    void deleteBedRoomById(int id);

}
