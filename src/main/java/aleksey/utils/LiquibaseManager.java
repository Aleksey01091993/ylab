package aleksey.utils;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public final class LiquibaseManager {
    private static final String CHANGELOG = "liquibase.changelog";

    private LiquibaseManager() {}


    public static void updateDataBase() {
        try(Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(ConnectionManager.get()))) {
            Liquibase liquibase = new Liquibase(PropertiesUtils.get(CHANGELOG), new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println("Liquibase complete.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
