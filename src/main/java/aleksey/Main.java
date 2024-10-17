package aleksey;

import aleksey.utils.LiquibaseManager;

public class Main {

    public static void main(String[] args) throws Exception {
        //MainMenu mainMenu = new MainMenu();
        //mainMenu.start();
        LiquibaseManager.updateDataBase();

    }


}
