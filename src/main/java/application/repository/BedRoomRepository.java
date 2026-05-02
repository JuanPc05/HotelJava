package application.repository;

import application.domain.BedRoom;
import application.domain.BedRoomType;
import application.domain.enums.BedRoomEnums;
import application.domain.enums.BedRoomState;
import application.service.ports.BedRoomRepositoryPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BedRoomRepository implements BedRoomRepositoryPort {


    List<BedRoom> bedRooms = new ArrayList<>(
            Arrays.asList(
                    new BedRoom(1, "201", new BedRoomType(1,"Indivual"),120000, BedRoomState.DISPONIBLE.getDescription()),
                    new BedRoom(2, "202", new BedRoomType(1,"Doble"),180000, BedRoomState.OCUPADA.getDescription() ),
                    new BedRoom(3, "203", new BedRoomType(1,"Suite"),240000, BedRoomState.RESERVADA.getDescription())
            ));

    public BedRoom saveBedRoom(BedRoom bedRoom){

        bedRooms.add(bedRoom);

        return bedRoom;

    }

    @Override
    public BedRoom updateBedRoom(BedRoom bedRoom) {

        for(int i = 0; i< bedRooms.size(); i++){
            if(bedRooms.get(i).getRoomId() == bedRoom.getRoomId()){
                bedRooms.set(i, bedRoom);
                return bedRoom;
            }
        }
        throw new IllegalArgumentException("Habitación con Id " + bedRoom.getRoomId() + "no encontrada");
    }


    @Override
    public Optional<BedRoom> findBedRoomById(int id) {

        for(BedRoom bedroom: bedRooms){
            if(bedroom.getRoomId() == id){
                return Optional.of(bedroom);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<BedRoom> findAllBedRooms() {

        return bedRooms;

    }

    @Override
    public void deleteBedRoomById(int id) {

        for(BedRoom bedroom: bedRooms){
            if(bedroom.getRoomId() == id){
                bedRooms.remove(bedroom);
                System.out.println("Habitacion con id " + id + " ha sido eliminada.");
            }
        }
         System.out.println("Habitacion con id " + id + " no encontrada.");

    }
}
