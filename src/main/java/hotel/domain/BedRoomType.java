package hotel.domain;

public class BedRoomType {

    private int idType;
    private String type;

    public BedRoomType(int idType, String type) {
        this.idType = idType;
        this.type = type;
    }

    public BedRoomType() {
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BedRoomType{" +
                "idType=" + idType +
                ", type='" + type + '\'' +
                '}';
    }
}
