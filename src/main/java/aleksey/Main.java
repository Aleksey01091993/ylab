package aleksey;

import aleksey.concoleinterface.MainMenu;
import aleksey.utils.ConnectionManager;

public class Main {

    public static void main(String[] args) throws Exception {
        //MainMenu mainMenu = new MainMenu();
        //mainMenu.start();

        try(var connection = ConnectionManager.open()) {
            System.out.println(connection.getTransactionIsolation());
        }
    }


}
