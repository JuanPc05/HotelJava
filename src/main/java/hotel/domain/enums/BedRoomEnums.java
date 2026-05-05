package hotel.domain.enums;

public enum BedRoomEnums {

    INDIVIDUAL ("Individual"),
    DOBLE ("Doble"),
    SUITE("Suite");


    String description;

     BedRoomEnums(String description) {
        this.description = description;
    }

     public String getDescription() {
        return description;
    }

}
