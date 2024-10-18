package aleksey.repository.implDB;

import aleksey.model.Person;
import aleksey.utils.ConnectionManager;
import aleksey.utils.PropertiesUtils;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
public class PersonRepositoryDBTest {


    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.2")
            .withDatabaseName(PropertiesUtils.get("db.username"))
            .withUsername(PropertiesUtils.get("db.username"))
            .withPassword(PropertiesUtils.get("db.password"))
            .withInitScript("personDB.sql");



    @Test
    void create() {

    }

    @Test
    void getPassword() {
    }

    @Test
    void update() {
    }

    @Test
    void getPerson() {
    }

    @Test
    void remove() {
    }
}