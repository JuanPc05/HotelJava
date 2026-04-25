package application.configuration;

import application.domain.Guest;
import application.repository.BedRoomRepository;
import application.repository.BedRoomTypeRepository;
import application.repository.EmployeeRepository;
import application.repository.GuestRepository;
import application.service.BedRoomServiceImp;
import application.service.EmployeeServiceImpl;
import application.service.GuestAdminServiceImpl;
import application.service.GuestServiceImpl;
import application.service.outputs.BedRoomService;
import application.service.outputs.EmployeeService;
import application.service.outputs.GuestAdminService;
import application.service.outputs.GuestService;
import application.service.ports.BedRoomRepositoryPort;
import application.service.ports.BedRoomTypeRepositoryPort;
import application.service.ports.EmployeeRepositoryPort;
import application.service.ports.GuestRepositoryPort;
import application.userinterface.MenuApp;
import application.userinterface.MenuBedRoom;
import application.userinterface.MenuEmployee;
import application.userinterface.MenuGuest;
import application.view.BedRoomView;
import application.view.EmployeeView;
import application.view.GuestView;

public class Config {

    public static MenuApp createMenuApp(){

        // 1. Repositorios (Adaptadores de Salida)
        GuestRepositoryPort guestRepo = new GuestRepository();
        EmployeeRepositoryPort empRepo = new EmployeeRepository();
        BedRoomRepositoryPort roomRepo = new BedRoomRepository();
        BedRoomTypeRepositoryPort typeRepo = new BedRoomTypeRepository(); // Si lo tienes

        // 2. Servicios (Core del negocio)
        GuestService guestService = new GuestServiceImpl(guestRepo);
        EmployeeService empService = new EmployeeServiceImpl(empRepo);
        BedRoomService roomService = new BedRoomServiceImp(roomRepo, typeRepo);

        // 3. Vistas (Traductores de consola)
        GuestView guestView = new GuestView(guestService, null); // Ajustar según tu constructor
        EmployeeView empView = new EmployeeView(empService);
        BedRoomView roomView = new BedRoomView(roomService);

        // 4. Menús Específicos
        MenuGuest menuGuest = new MenuGuest(guestView);
        MenuEmployee menuEmployee = new MenuEmployee(empView);
        MenuBedRoom menuBedRoom = new MenuBedRoom(roomView);

        return new MenuApp(menuGuest,menuEmployee,menuBedRoom);
    }






}
