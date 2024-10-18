package aleksey.utils;

public final class QuickStart {

    static {
        LiquibaseManager.updateDataBase();
    }

    private QuickStart() {
    }

    public static void startInMemory() {
        Manager.inMemory().start();
    }

    public static void startInDB() {
        Manager.inDB().start();
    }
}
