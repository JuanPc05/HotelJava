package hotel.domain.enums;

public enum BedRoomState {


    DISPONIBLE(" Habitación Disponible"),
    OCUPADA (" Habitación Ocupada"),
    MANTENIMIENTO (" Habitación en Mantenimiento"),
    RESERVADA(" Habitación Reservada");


    private final String description;

    BedRoomState(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
