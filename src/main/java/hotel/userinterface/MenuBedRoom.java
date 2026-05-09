package hotel.userinterface;

import hotel.domain.BedRoomType;
import hotel.infraesctructure.in.view.adapter.BedRoomTypeView;
import hotel.infraesctructure.util.FormValidationUtil;
import hotel.infraesctructure.in.view.adapter.BedRoomView;

public class MenuBedRoom {
    private final BedRoomView bedRoomView;
    private final BedRoomTypeView bedRoomTypeView;

    public MenuBedRoom(BedRoomView bedRoomView, BedRoomTypeView bedRoomTypeView) {
        this.bedRoomView = bedRoomView;
        this.bedRoomTypeView = bedRoomTypeView;
    }

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- GESTIÓN DE HABITACIONES ---");
            System.out.println("1. Registrar Habitación");
            System.out.println("2. Actualizar Habitación");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Listar todas las Habitaciones");
            System.out.println("5. Eliminar Habitación");
            System.out.println("6. Crear tipo de habitacion");
            System.out.println("7. Listar tipos de habitacion");
            System.out.println("8. Volver al menú principal");

            int option = FormValidationUtil.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1 -> bedRoomView.createBedRoom();
                case 2 -> bedRoomView.updateBedRoom();
                case 3 -> bedRoomView.getBedRoomById();
                case 4 -> bedRoomView.getAllBedRooms();
                case 5 -> bedRoomView.deleteBedRoomById();
                case 6 -> bedRoomTypeView.createBedRoomTypeView();
                case 7 -> System.out.println("nada...");
                case 8 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}