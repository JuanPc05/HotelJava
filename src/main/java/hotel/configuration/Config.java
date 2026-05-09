package hotel.configuration;

import java.sql.Connection;
import hotel.domain.Guest;
import hotel.infraesctructure.in.view.adapter.*;
import hotel.infraesctructure.out.adapter.*;
import hotel.application.*;
import hotel.application.inputs.*;
import hotel.application.ports.*;
import hotel.infraesctructure.out.db.DatabaseConnectionMySQL;
import hotel.infraesctructure.out.mapper.BedRoomRowMapper;
import hotel.infraesctructure.out.mapper.RowMapper;
import hotel.userinterface.*;

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
        BookingRepositoryPort bookingRepo = new BookingRepository();

        // (Nota: asegúrate de que el método realmente se llame getConnetion sin la 'c', o corrígelo a getConnection si te da error)
        Connection connection = DatabaseConnectionMySQL.getInstance().getConnetion();
        RowMapper bedRoomTypeRowMapper = new BedRoomRowMapper();
        // Aquí creaste la variable bedRoomTypeRepo
        BedRoomTypeRepositoryPort bedRoomTypeRepo = new BedRoomTypeRepositoryDb(connection, bedRoomTypeRowMapper);
        BedRoomTypeUseCase bedRoomTypeUseCase = new BedRoomTypeInputAdapter(bedRoomTypeRepo);
        BedRoomTypeView bedRoomTypeView = new BedRoomTypeView(bedRoomTypeUseCase);

        // 2. Servicios (Core del negocio)
        GuestService guestService = new GuestServiceImpl(guestRepo);
        GuestAdminService guestAdminService = new GuestAdminServiceImpl(guestAdminRepo);
        EmployeeService empService = new EmployeeServiceImpl(empRepo);

        // ¡Aquí está la corrección! Pasamos bedRoomTypeRepo en lugar de typeRepo
        BedRoomService roomService = new BedRoomServiceImp(roomRepo, bedRoomTypeRepo);

        BookingService bookingService = new BookingServiceImpl(bookingRepo, guestRepo, roomRepo);

        // 3. Vistas (Traductores de consola)
        GuestView guestView = new GuestView(guestService, guestAdminService);
        EmployeeView empView = new EmployeeView(empService);
        BedRoomView roomView = new BedRoomView(roomService);
        BookingView bookingView = new BookingView(bookingService);

        // 4. Menús Específicos
        MenuGuest menuGuest = new MenuGuest(guestView);
        MenuEmployee menuEmployee = new MenuEmployee(empView);
        MenuBedRoom menuBedRoom = new MenuBedRoom(roomView, bedRoomTypeView);
        MenuBooking menuBooking = new MenuBooking(bookingView);

        return new MenuApp(menuGuest,menuEmployee,menuBedRoom,menuBooking);
    }
}