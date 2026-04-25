package application.view;

import application.domain.BedRoom;
import application.domain.BedRoomType;
import application.service.BedRoomStateSelector;
import application.service.outputs.BedRoomService;
import application.util.FormValidationUtil;

import java.util.Optional;

public class BedRoomView {

    private final BedRoomService bedRoomService;

    public BedRoomView(BedRoomService bedRoomService) {
        this.bedRoomService = bedRoomService;
    }

    // --- ACCIONES DEL MENÚ ---

    public void createBedRoom() {
        System.out.println("\n--- REGISTRAR HABITACIÓN ---");
        try {
            // 1. Capturamos los datos y creamos el OBJETO
            BedRoom newRoom = captureBedRoomData(null);

            // 2. PASAMOS EL OBJETO COMPLETO AL SERVICIO (Esto corrige el error)
            bedRoomService.createBedRoom(newRoom);

            System.out.println("Habitación creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateBedRoom() {
        System.out.println("\n--- ACTUALIZAR HABITACIÓN ---");
        int id = FormValidationUtil.validateInt("Ingrese el ID de la habitación a actualizar: ");

        Optional<BedRoom> existing = bedRoomService.getBedRoomById(id);

        if (existing.isPresent()) {
            printBedRoomDetails(existing.get()); // Mostramos el estado actual
            BedRoom updated = captureBedRoomData(id);

            bedRoomService.updateBedRoom(updated);

            System.out.println("Habitación actualizada.");
        } else {
            System.out.println("Error: Habitación no encontrada.");
        }
    }

    public void getBedRoomById() {
        int id = FormValidationUtil.validateInt("ID a buscar: ");
        bedRoomService.getBedRoomById(id)
                .ifPresentOrElse(
                        this::printBedRoomDetails,
                        () -> System.out.println("No se encontró la habitación.")
                );
    }

    public void getAllBedRooms() {
        System.out.println("\n--- LISTADO DE HABITACIONES ---");
        bedRoomService.getAllBedRooms().forEach(this::printBedRoomDetails);
    }

    public void deleteBedRoomById() {
        int id = FormValidationUtil.validateInt("ID a eliminar: ");
        bedRoomService.deleteBedRoomById(id);
        System.out.println("Proceso de eliminación completado.");
    }

    // --- MÉTODOS DE APOYO (REUTILIZACIÓN) ---

    private BedRoom captureBedRoomData(Integer fixedId) {
        int id = (fixedId != null) ? fixedId : FormValidationUtil.validateInt("ID de la Habitación: ");
        String roomNumber = FormValidationUtil.validateString("Número de habitación: ");

        // 1. Pedimos el ID del tipo
        int typeId = FormValidationUtil.validateInt("Tipo (1. Sencilla, 2. Doble, 3. Suite): ");

        // 2. Mapeamos el ID a un nombre (Lógica simple de vista)
        String typeName = switch (typeId) {
            case 1 -> "Sencilla";
            case 2 -> "Doble";
            case 3 -> "Suite";
            default -> "Estándar";
        };

        // 3. Instanciamos el objeto BedRoomType
        BedRoomType typeObject = new BedRoomType(typeId, typeName);

        double price = FormValidationUtil.validateDouble("Precio por noche: ");
        String state = BedRoomStateSelector.bedRoomAddState();

        // 4. Se lo pasamos al constructor de BedRoom
        return new BedRoom(id, roomNumber, typeObject, price, state);
    }

    private void printBedRoomDetails(BedRoom b) {
        System.out.printf("ID: %d | Num: %s | Tipo: %s | Precio: %.2f | Estado: %s%n",
                b.getRoomId(),
                b.getRoom(),
                b.getBedRoomType().getType(),
                b.getPrice(),
                b.getState());
    }
}