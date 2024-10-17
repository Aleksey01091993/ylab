package aleksey.repository.implDB;

import aleksey.model.Person;
import aleksey.model.Tracking;
import aleksey.repository.TrackingRepository;

import java.time.LocalDate;
import java.util.List;

public class TrackingRepositoryDB implements TrackingRepository {
    @Override
    public void newPerson(Person person) {

    }

    @Override
    public void removePerson(String email) {

    }

    @Override
    public List<Tracking> getStatistic(Person person, LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public Tracking add(Person person, Tracking tracking) {
        return null;
    }

    @Override
    public List<Tracking> getTrackingAndHabitName(Person person, String name) {
        return List.of();
    }

    @Override
    public List<Tracking> getCurrentTracking(Person person) {
        return List.of();
    }

    @Override
    public List<Tracking> getAllTracking(Person person) {
        return List.of();
    }
}
