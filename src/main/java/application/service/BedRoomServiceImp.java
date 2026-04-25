package application.service;

import application.domain.BedRoom;
import application.domain.BedRoomType;
import application.service.outputs.BedRoomService;
import application.service.ports.BedRoomRepositoryPort;
import application.service.ports.BedRoomTypeRepositoryPort;

import java.util.List;
import java.util.Optional;

public class BedRoomServiceImp implements BedRoomService {

    private final BedRoomRepositoryPort bedRoomRepositoryPort;
    private final BedRoomTypeRepositoryPort bedRoomTypeRepositoryPort;

    public BedRoomServiceImp(BedRoomRepositoryPort bedRoomRepositoryPort, BedRoomTypeRepositoryPort bedRoomTypeRepositoryPort) {
        this.bedRoomRepositoryPort = bedRoomRepositoryPort;
        this.bedRoomTypeRepositoryPort = bedRoomTypeRepositoryPort;
    }

    @Override
    public BedRoom createBedRoom(BedRoom bedRoom) {
        // 1. Validar duplicados
        if (bedRoomRepositoryPort.findBedRoomById(bedRoom.getRoomId()).isPresent()) {
            throw new IllegalArgumentException("La habitación con ID " + bedRoom.getRoomId() + " ya existe.");
        }

        // 2. Resolver el tipo de habitación (Regla de negocio)
        // Obtenemos el ID que el usuario puso en la vista y buscamos el objeto real
        BedRoomType officialType = findOfficialType(bedRoom.getBedRoomType().getIdType());
        bedRoom.setBedRoomType(officialType);

        // 3. Guardar
        return bedRoomRepositoryPort.saveBedRoom(bedRoom);
    }

    @Override
    public BedRoom updateBedRoom(BedRoom bedRoom) {
        // 1. Verificar existencia
        if (bedRoomRepositoryPort.findBedRoomById(bedRoom.getRoomId()).isEmpty()) {
            throw new IllegalArgumentException("No se puede actualizar: la habitación no existe.");
        }

        // 2. Resolver el tipo nuevamente por si cambió
        BedRoomType officialType = findOfficialType(bedRoom.getBedRoomType().getIdType());
        bedRoom.setBedRoomType(officialType);

        // 3. Actualizar en persistencia
        return bedRoomRepositoryPort.updateBedRoom(bedRoom);
    }

    @Override
    public Optional<BedRoom> getBedRoomById(int id) {
        return bedRoomRepositoryPort.findBedRoomById(id);
    }

    @Override
    public List<BedRoom> getAllBedRooms() {
        return bedRoomRepositoryPort.findAllBedRooms();
    }

    @Override
    public void deleteBedRoomById(int id) {
        bedRoomRepositoryPort.deleteBedRoomById(id);
    }

    // Método privado auxiliar (más descriptivo)
    private BedRoomType findOfficialType(int typeId) {
        return bedRoomTypeRepositoryPort.findBedRoomTypeById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("ID de tipo de habitación no válido: " + typeId));
    }
}