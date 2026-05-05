package hotel.application.ports;

import hotel.domain.BedRoomType;

import java.util.Optional;

public interface BedRoomTypeRepositoryPort {

    BedRoomType saveBedRoomType();
    Optional<BedRoomType> findBedRoomTypeById(int id);

}
