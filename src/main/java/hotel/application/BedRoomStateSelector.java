package hotel.application;

import hotel.domain.enums.BedRoomState;
import hotel.infraesctructure.util.FormValidationUtil;

public class BedRoomStateSelector {

    public static String bedRoomAddState(){

        System.out.println("Seleccione 1. Disponible 2. Ocupada 3. En Mantenimiento 4. Reservada ");
        String value = "";

        int option = FormValidationUtil.validateInt("Opción");

        switch (option){
            case 1:
                value = BedRoomState.DISPONIBLE.getDescription();
                break;
            case 2:
                value = BedRoomState.OCUPADA.getDescription();
                break;
            case 3:
                value = BedRoomState.MANTENIMIENTO.getDescription();
                break;
            case 4:
                value = BedRoomState.RESERVADA.getDescription();
            default:
                value = "Opcion Errada";

        }

        return value;
    }

}
