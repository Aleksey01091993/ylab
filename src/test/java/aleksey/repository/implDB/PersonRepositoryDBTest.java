package aleksey.repository.implDB;

import aleksey.model.Person;
import aleksey.utils.ConnectionManager;
import aleksey.utils.PropertiesUtils;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
public class PersonRepositoryDBTest {


    private static final DockerImageName POSTGRESQL_IMAGE = DockerImageName.parse("postgres:17.0");
    private static final PersonRepositoryDB repository = PersonRepositoryDB.getInstance();
    private static final String INIT_SQL = """
                    create schema if not exists habit;
                    alter schema habit owner to postgres;
                    create sequence public.user_id;
                    alter sequence public.user_id owner to postgres;
                    create table if not exists habit.users
                    (
                        id       bigint default nextval('user_id'::regclass) not null,
                        name     varchar(55),
                        email    varchar(55)                                 not null
                            unique,
                        password varchar(55)                                 not null,
                        primary key (id, email)
                    );
                    alter table habit.users owner to postgres;
                    INSERT INTO habit.users (name, email, password) VALUES ('t_user', 't_email', 't_password');
                    INSERT INTO habit.users (name, email, password) VALUES ('t_user_2', 't_email_2', 't_password_2');
                    INSERT INTO habit.users (name, email, password) VALUES ('t_user_3', 't_email_3', 't_password_3');
                    """;
    private static final String DESTROY_SQL = """
            drop table if exists habit.users cascade;
            drop sequence if exists public.user_id cascade;
            drop schema if exists habit cascade;
            """;
    @ClassRule
    public static GenericContainer<?> postgreSQLContainer = new GenericContainer<>(POSTGRESQL_IMAGE)
            .withEnv(Map.of("POSTGRES_DB", PropertiesUtils.get("db.username"),
                            "POSTGRES_PASSWORD", PropertiesUtils.get("db.password"),
                            "POSTGRES_USER", PropertiesUtils.get("db.username")))
            .withExposedPorts(5432);



    @BeforeEach
    void setUp() {
        postgreSQLContainer.start();
        try(var connection = ConnectionManager.get();
            var statement = connection.createStatement()) {
            statement.execute(INIT_SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        try(var connection = ConnectionManager.get();
            var statement = connection.createStatement()) {
            statement.executeUpdate(DESTROY_SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        postgreSQLContainer.stop();
    }



    @Test
    void create() {
        System.out.println(postgreSQLContainer.getContainerInfo());
        Person person = repository.create(new Person("email", "name", "password"));
        assertEquals(person, repository.getPerson("email"));
        System.out.println(person);
    }

    @Test
    void getPassword() {
        assertEquals(repository.getPassword("t_email"), "t_password");
    }

    @Test
    void update() {
        Person person = new Person("t_email", "new_name", "t_password");
        repository.update("t_email", person);
        assertEquals(person, repository.getPerson("t_email"));
    }

    @Test
    void getPerson() {
        assertEquals(new Person("t_email", "t_user", "t_password"), repository.getPerson("t_email"));
    }

    @Test
    void remove() {
        repository.remove("t_email");
        assertNull(repository.getPerson("t_email"));
    }
}