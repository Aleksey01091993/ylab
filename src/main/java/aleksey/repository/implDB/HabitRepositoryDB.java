package aleksey.repository.implDB;

import aleksey.model.Habit;
import aleksey.model.Person;
import aleksey.repository.HabitRepository;
import aleksey.utils.ConnectionManager;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitRepositoryDB implements HabitRepository {

    private static final HabitRepositoryDB INSTANCE = new HabitRepositoryDB();

    private static final String CREATE_SQL = """
            INSERT INTO habit.habit (name, description, create_date, frequency, user_email) VALUES (?, ?, ?, ?, ?);
            """;

    private static final String REMOVE_SQL = """
            DELETE FROM habit.habit WHERE name = ? AND user_email = ?;
            """;

    private static final String GET_ALL_SQL = """
            SELECT name, description, create_date, frequency FROM habit.habit WHERE user_email = ?;
            """;

    private static final String UPDATE_SQL = """
            UPDATE habit.habit SET name = ?, description = ?, frequency = ?
                   WHERE name = ? AND user_email = ?;
            """;

    private static final String GET_SQL = """
            SELECT name, description, create_date, frequency FROM habit.habit WHERE user_email = ? AND name = ?;
            """;

    private HabitRepositoryDB() {
    }

    @Override
    public Habit createHabit(String personEmail, Habit habit) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, habit.getName());
            statement.setString(2, habit.getDescription());
            statement.setDate(3, Date.valueOf(habit.getCreateDate()));
            statement.setString(4, habit.getFrequency());
            statement.setString(5, personEmail);
            statement.executeUpdate();
            return habit;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void newPerson(Person person) {
    }

    @Override
    public void removePerson(String email) {
    }

    @Override
    public void removeHabit(String personEmail, String habitName) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(REMOVE_SQL)) {
            statement.setString(1, habitName);
            statement.setString(2, personEmail);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Habit> getHabits(String personEmail) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(GET_ALL_SQL)) {
            var result = statement.executeQuery();
            List<Habit> habits = new ArrayList<>();
            while (result.next()) {
                habits.add(
                        new Habit(
                                result.getDate("create_date").toLocalDate(),
                                result.getString("description"),
                                result.getString("frequency"),
                                result.getString("name")
                        )
                );
            }
            return habits;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Habit getHabit(String personEmail, String habitName) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(GET_SQL)) {
            statement.setString(1, personEmail);
            statement.setString(2, habitName);
            var result = statement.executeQuery();
            Habit habit = null;
            if (result.next()) {
                habit = new Habit(
                        result.getDate("create_date").toLocalDate(),
                        result.getString("description"),
                        result.getString("frequency"),
                        result.getString("name")
                );
            }
            return habit;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Habit updateHabit(String personEmail, Habit habit, String habitNewName) {
        Habit updatedHabit = getHabit(personEmail, habit.getName());
        if(updatedHabit == null) {
            return null;
        }
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            if (habit.getName() != null) {
                statement.setString(1, habit.getName());
            } else {
                statement.setString(1, habit.getName());
            }
            if (habit.getDescription() != null) {
                statement.setString(2, habit.getDescription());
            } else {
                statement.setString(2, updatedHabit.getDescription());
            }
            if (habit.getFrequency() != null) {
                statement.setString(3, habit.getFrequency());
            } else {
                statement.setString(3, updatedHabit.getFrequency());
            }
            statement.executeUpdate();
            return updatedHabit;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getHabitsName(String personEmail) {
        return List.of();
    }

    public static HabitRepositoryDB getInstance() {
        return INSTANCE;
    }
}
