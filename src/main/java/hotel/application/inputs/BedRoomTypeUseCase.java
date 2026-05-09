package hotel.application.inputs;

import hotel.domain.BedRoomType;

import java.util.List;

public interface BedRoomTypeUseCase {
    BedRoomType createBedRoomType(int typeId,String type);
    List<BedRoomType> findAllBedRoomTypes();
}
