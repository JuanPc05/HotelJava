package hotel.application;

import hotel.application.inputs.BedRoomTypeUseCase;
import hotel.application.ports.BedRoomTypeRepositoryPort;
import hotel.domain.BedRoomType;

import java.util.List;

public class BedRoomTypeInputAdapter implements BedRoomTypeUseCase {

    private final BedRoomTypeRepositoryPort bedRoomTypeRepositoryPort;

    public BedRoomTypeInputAdapter(BedRoomTypeRepositoryPort bedRoomTypeRepositoryPort) {
        this.bedRoomTypeRepositoryPort = bedRoomTypeRepositoryPort;
    }


    @Override
    public BedRoomType createBedRoomType(int typeId, String type) {
        BedRoomType bedRoomType = new BedRoomType(typeId, type);

        bedRoomTypeRepositoryPort.saveBedRoomType(bedRoomType);

        return bedRoomType;
    }

    @Override
    public List<BedRoomType> findAllBedRoomTypes() {

        return  bedRoomTypeRepositoryPort.findAllBedRoomTypes();
    }
}
