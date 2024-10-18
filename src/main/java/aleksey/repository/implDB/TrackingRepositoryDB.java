package aleksey.repository.implDB;

import aleksey.model.Person;
import aleksey.model.Tracking;
import aleksey.repository.TrackingRepository;
import aleksey.utils.ConnectionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrackingRepositoryDB implements TrackingRepository {

    private static final TrackingRepositoryDB instance = new TrackingRepositoryDB();

    private static final String GET_STATISTICS_SQL = """
            SELECT USER_EMAIL, HABIT_NAME, DATE_CREATE, IS_MARK FROM habit.tracking 
            WHERE user_email = ? AND date_create BETWEEN ? AND ?;
            """;
    private static final String CREATE_SQL = """
                        INSERT INTO habit.tracking (user_email, habit_name, date_create, is_mark) VALUES (?, ?, ?, ?);
            """;
    private static final String GET_HABIT_NAME_SQL = """
                        SELECT USER_EMAIL, HABIT_NAME, DATE_CREATE, IS_MARK FROM habit.tracking 
                        WHERE user_email = ? AND habit_name = ?;
            """;
    private static final String GET_CURRENT_SQL = """
            SELECT USER_EMAIL, HABIT_NAME, DATE_CREATE, IS_MARK FROM habit.tracking 
                        WHERE user_email = ? AND date_create = ?;
            """;
    private static final String GET_ALL = """
            SELECT USER_EMAIL, HABIT_NAME, DATE_CREATE, IS_MARK FROM habit.tracking 
                        WHERE user_email = ?;
            """;

    @Override
    public void newPerson(Person person) {

    }

    @Override
    public void removePerson(String email) {

    }

    public static TrackingRepositoryDB getInstance() {
        return instance;
    }

    @Override
    public List<Tracking> getStatistic(Person person, LocalDate startDate, LocalDate endDate) {
        try (var connection = ConnectionManager.get(); var statement = connection.prepareStatement(GET_STATISTICS_SQL)) {
            statement.setString(1, person.getEmail());
            statement.setDate(2, Date.valueOf(startDate));
            statement.setDate(3, Date.valueOf(endDate));
            return trackingList(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tracking add(Person person, Tracking tracking) {
        try (var connection = ConnectionManager.get(); var statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, person.getName());
            statement.setString(2, tracking.getHabitName());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setBoolean(4, true);
            statement.executeUpdate();
            return tracking;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tracking> getTrackingAndHabitName(Person person, String name) {
        try (var connection = ConnectionManager.get(); var statement = connection.prepareStatement(GET_HABIT_NAME_SQL)) {
            statement.setString(1, person.getEmail());
            statement.setString(2, name);
            return trackingList(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tracking> getCurrentTracking(Person person) {
        try (var connection = ConnectionManager.get(); var statement = connection.prepareStatement(GET_CURRENT_SQL)) {
            statement.setString(1, person.getEmail());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            return trackingList(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tracking> getAllTracking(Person person) {
        try (var connection = ConnectionManager.get(); var statement = connection.prepareStatement(GET_ALL)) {
            statement.setString(1, person.getEmail());
            return trackingList(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Tracking> trackingList(PreparedStatement statement) {
        try (statement) {
            var result = statement.executeQuery();
            List<Tracking> trackings = new ArrayList<>();
            while (result.next()) {
                trackings.add(new Tracking(result.getDate("date_create").toLocalDate(), result.getString("habit_name"), result.getBoolean("is_mark"), result.getString("user_email")));
            }
            return trackings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
