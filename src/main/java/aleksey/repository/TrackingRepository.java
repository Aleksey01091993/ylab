package aleksey.repository;

import aleksey.model.Person;
import aleksey.model.Tracking;

import java.time.LocalDate;
import java.util.List;

public interface TrackingRepository {
    void newPerson(Person person);

    void removePerson(String email);

    List<Tracking> getStatistic(Person person, LocalDate startDate, LocalDate endDate);

    Tracking add(Person person, Tracking tracking);

    List<Tracking> getTrackingAndHabitName(Person person, String name);

    List<Tracking> getCurrentTracking(Person person);

    List<Tracking> getAllTracking(Person person);
}
