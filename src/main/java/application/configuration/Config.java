package application.configuration;

import application.domain.Guest;
import application.repository.*;
import application.service.*;
import application.service.outputs.*;
import application.service.ports.*;
import application.userinterface.*;
import application.view.BedRoomView;
import application.view.BookingView;
import application.view.EmployeeView;
import application.view.GuestView;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Config {

    public static MenuApp createMenuApp(){
        List<Guest> database = new ArrayList<>(Arrays.asList(
                new Guest(1, "Maria", "Gomez","mg@mail.com", "123456" , true , "Medellín" , "Nuevo"),
                new Guest(2, "Juan", "Perez", "juan@mail.com","12345678", true , "Bogotá", "Frecuente")
        ));

        // 1. Repositorios (Adaptadores de Salida)
        GuestRepositoryPort guestRepo = new GuestRepository(database);
        GuestAdminRepositoryPort guestAdminRepo = new GuestAdminRepository(database);
        EmployeeRepositoryPort empRepo = new EmployeeRepository();
        BedRoomRepositoryPort roomRepo = new BedRoomRepository();
        BedRoomTypeRepositoryPort typeRepo = new BedRoomTypeRepository(); // Si lo tienes
        BookingRepositoryPort bookingRepo = new BookingRepository();

        // 2. Servicios (Core del negocio)
        GuestService guestService = new GuestServiceImpl(guestRepo);
        GuestAdminService guestAdminService = new GuestAdminServiceImpl(guestAdminRepo);
        EmployeeService empService = new EmployeeServiceImpl(empRepo);
        BedRoomService roomService = new BedRoomServiceImp(roomRepo, typeRepo);
        BookingService bookingService = new BookingServiceImpl(bookingRepo, guestRepo, roomRepo);

        // 3. Vistas (Traductores de consola)
        GuestView guestView = new GuestView(guestService, guestAdminService); // Ajustar según tu constructor
        EmployeeView empView = new EmployeeView(empService);
        BedRoomView roomView = new BedRoomView(roomService);
        BookingView bookingView = new BookingView(bookingService);

        // 4. Menús Específicos
        MenuGuest menuGuest = new MenuGuest(guestView);
        MenuEmployee menuEmployee = new MenuEmployee(empView);
        MenuBedRoom menuBedRoom = new MenuBedRoom(roomView);
        MenuBooking menuBooking = new MenuBooking(bookingView);

        return new MenuApp(menuGuest,menuEmployee,menuBedRoom,menuBooking);
    }






}
