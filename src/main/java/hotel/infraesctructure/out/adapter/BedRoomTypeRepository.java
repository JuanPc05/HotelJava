package hotel.infraesctructure.out.adapter;

import hotel.domain.BedRoomType;
import hotel.application.ports.BedRoomTypeRepositoryPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BedRoomTypeRepository {


    List<BedRoomType> bedRoomTypes = new ArrayList<>(
            Arrays.asList(
                    new BedRoomType(1, "Single"),
                    new BedRoomType(2, "Doble"),
                    new BedRoomType(3, "suit"),
                    new BedRoomType(4, "Grupal")

            )
    );


    public BedRoomType saveBedRoomType() {
        return null;
    }


    public Optional<BedRoomType> findBedRoomTypeById(int id) {

        for(BedRoomType bedRoomType : bedRoomTypes){
            if(id == bedRoomType.getIdType()){
                return Optional.of(bedRoomType);
            }
        }


        return Optional.empty();
    }
}
