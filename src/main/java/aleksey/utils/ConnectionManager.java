package aleksey.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL = "db.uri";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";

    private ConnectionManager() {}

    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtils.get(URL),
                    PropertiesUtils.get(USERNAME),
                    PropertiesUtils.get(PASSWORD)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
