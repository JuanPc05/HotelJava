package hotel.infraesctructure.in.view.adapter;

import hotel.domain.BedRoomType;

import java.util.Scanner;

public class BedRoomTypeView {

    Scanner sc = new Scanner(System.in);

    private final BedRoomType bedRoomType;

    public BedRoomTypeView(BedRoomType bedRoomType){
        this.bedRoomType = bedRoomType;
    }

    public void createBedRoomTypeView() {
        System.out.println("Creating a new bedroom type...");
        System.out.println("Ingrese el id del tipo de habitación:");
        int idType = sc.nextInt();
        sc.nextLine();
        bedRoomType.setIdType(idType);
        System.out.println("Ingrese el nombre del tipo de habitación:");
        String nameType = sc.nextLine();
        bedRoomType.setType(nameType);
    }




}
