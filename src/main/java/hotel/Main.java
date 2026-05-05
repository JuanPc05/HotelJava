package hotel;

import hotel.configuration.Config;
import hotel.infraesctructure.out.db.DatabaseConnectionMySQL;
import hotel.userinterface.MenuApp;

public class Main {

    public static void main(String[] args) {

        MenuApp menuApp = Config.createMenuApp();

        menuApp.showMainMenu();

    }
}
