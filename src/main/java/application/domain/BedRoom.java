package application.domain;

import java.util.Scanner;

public class BedRoom {

    private int roomId;
    private String room;
    private BedRoomType bedRoomType;
    private double price;
    private String state;


    public BedRoom(){

    }

    public BedRoom(int roomId, String room,BedRoomType type,double price, String state) {
        this.roomId = roomId;
        this.room = room;
        this.bedRoomType = type;
        this.price = price;
        this.state = state;
    }

    public BedRoom(String room) {
        this.room = room;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BedRoomType getBedRoomType() {
        return bedRoomType;
    }

    public void setBedRoomType(BedRoomType bedRoomType) {
        this.bedRoomType = bedRoomType;
    }

    @Override
    public String toString() {
        // Validamos si bedRoomType es null para evitar errores
        String nombreTipo = (bedRoomType != null) ? bedRoomType.getType() : "Sin tipo";

        return String.format(
                "Habitación [ID: %d | Nombre: %s | Tipo: %s | Precio: $%.2f | Estado: %s]",
                roomId, room, nombreTipo, price, state
        );
    }



}
