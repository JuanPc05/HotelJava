package hotel.application.ports;

import hotel.domain.BedRoomType;

import java.util.Optional;

public interface BedRoomTypeRepositoryPort {

    BedRoomType saveBedRoomType(BedRoomType bedRoomType);
    Optional<BedRoomType> findBedRoomTypeById(int id);

}
