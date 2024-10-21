package aleksey.utils;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtils {
    private static final Properties PROPERTIES = new Properties();

    private PropertiesUtils() {}

    static {
        loudProperties();
    }

    private static void loudProperties() {
        try(var inputStream = PropertiesUtils.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
