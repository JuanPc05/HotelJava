package hotel.infraesctructure.in.view.adapter;

import hotel.application.BedRoomTypeInputAdapter;
import hotel.application.inputs.BedRoomTypeUseCase;
import hotel.domain.BedRoomType;
import hotel.infraesctructure.util.FormValidationUtil;

import java.util.Scanner;

public class BedRoomTypeView {

    private final BedRoomTypeUseCase bedRoomTypeUseCase;

    public BedRoomTypeView(BedRoomTypeUseCase bedRoomTypeUseCase){
        this.bedRoomTypeUseCase = bedRoomTypeUseCase;
    }

    public void createBedRoomTypeView() {
        System.out.println("Creating a new bedroom type...");
        int idType = FormValidationUtil.validateInt("Ingrese el id del tipo de habitación:");
        String nameType = FormValidationUtil.validateString("Ingrese el nombre del tipo de habitación:");

        bedRoomTypeUseCase.createBedRoomType(idType, nameType);
    }


}
