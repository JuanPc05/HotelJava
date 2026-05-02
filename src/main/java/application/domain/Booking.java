package application.domain;

import application.domain.enums.StatusBooking;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {

    private int id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private StatusBooking status;
    private Guest guest;
    private BedRoom bedRoom;
    private double total;

    public Booking() {};

    public Booking(int id, LocalDate checkIn, LocalDate checkOut, StatusBooking status, Guest guest, BedRoom bedRoom) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.guest = guest;
        this.bedRoom = bedRoom;

        this.total = calculateTotal();
    }


    private double calculateTotal() {
        if (checkIn == null || checkOut == null || bedRoom == null) {
            return 0.0;
        }
        long days = ChronoUnit.DAYS.between(checkIn, checkOut);

        if (days == 0) days = 1;

        return days * bedRoom.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public StatusBooking getStatus() {
        return status;
    }

    public void setStatus(StatusBooking status) {
        this.status = status;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public BedRoom getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(BedRoom bedRoom) {
        this.bedRoom = bedRoom;
    }

    public double getTotal() {
        return total;
    }


    @Override
    public String toString() {

        return """
               ===========================================
               RESERVACIÓN #%d
               ===========================================
               ESTADO:      %s
               CLIENTE:     %s %s
               HABITACIÓN:  %s
               
               FECHAS:
                 - Check-In:  %s
                 - Check-Out: %s
               
               TOTAL A PAGAR: $%.2f
               ===========================================
               """.formatted(
                id, status, guest.getName(), guest.getLastName(),
                bedRoom.getRoom(), checkIn, checkOut, total
        );
    }
}