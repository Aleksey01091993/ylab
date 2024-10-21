package aleksey.repository.implDB;

import aleksey.model.Person;
import aleksey.repository.PersonRepository;
import aleksey.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public final class PersonRepositoryDB implements PersonRepository {

    private static final PersonRepositoryDB INSTANCE = new PersonRepositoryDB();
    private static final String CREATE_SQL = """
            INSERT INTO habit.users (name, email, password) VALUES (?, ?, ?);
            """;
    private static final String REMOVE_SQL = """
            DELETE FROM habit.users WHERE email = ?;
            """;
    private static final String GET_PERSON_SQL = """
            SELECT name, email, password FROM habit.users WHERE email = ?;
            """;
    private static final String GET_PASSWORD_SQL = """
            SELECT password FROM habit.users WHERE email = ?;
            """;
    private static final String UPDATE_SQL = """
            UPDATE HABIT.users SET name = ?, email = ?, password = ? 
                   WHERE email = ?;
            """;

    @Override
    public Person create(Person person) {
        try(var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getEmail());
            statement.setString(3, person.getPassword());
            statement.executeUpdate();
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPassword(String email) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(GET_PASSWORD_SQL)) {
            statement.setString(1, email);
            var result = statement.executeQuery();
            String password = null;
            if (result.next()) {
                password = result.getString("password");
            }
            return password;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person update(String email, Person person) {
        Person updatedPerson = getPerson(email);
        if(updatedPerson == null) {
            return null;
        }
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            if (person.getName() != null) {
                statement.setString(1, person.getName());
            } else {
                statement.setString(1, updatedPerson.getName());
            }
            if (person.getEmail() != null) {
                statement.setString(2, person.getEmail());
            } else {
                statement.setString(2, updatedPerson.getEmail());
            }
            if (person.getPassword() != null) {
                statement.setString(3, person.getPassword());
            } else {
                statement.setString(3, updatedPerson.getPassword());
            }
            statement.setString(4, email);
            statement.executeUpdate();
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person getPerson(String email) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(GET_PERSON_SQL)) {
            statement.setString(1, email);
            var result = statement.executeQuery();
            Person person = null;
            if (result.next()) {
                person = new Person(
                        result.getString("email"),
                        result.getString("name"),
                        result.getString("password")
                );
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(String email) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(REMOVE_SQL)) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PersonRepositoryDB getInstance() {
        return INSTANCE;
    }

    private PersonRepositoryDB() {}
}
