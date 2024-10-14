package aleksey.repository.impl;

import aleksey.model.Person;
import aleksey.model.Tracking;
import aleksey.repository.TrackingRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackingRepositoryImpl implements TrackingRepository {

    private final Map<String, List<Tracking>> tracking = new HashMap<>();

    @Override
    public void newPerson(Person person) {
        tracking.put(person.getEmail(), new ArrayList<>());
    }

    @Override
    public void removePerson(String email) {
        tracking.remove(email);
    }

    @Override
    public List<Tracking> getStatistic(Person person, LocalDate startDate, LocalDate endDate) {
        return tracking.get(person.getEmail()).stream()
                .filter(o1 -> o1.getDate().isAfter(startDate) && o1.getDate().isBefore(endDate)).toList();
    }

    @Override
    public Tracking add(Person person, Tracking tracking) {
        if (this.tracking.containsKey(person.getEmail())) {
            this.tracking.get(person.getEmail()).add(tracking);
            return tracking;
        }
        return null;
    }

    @Override
    public List<Tracking> getTrackingAndHabitName(Person person, String name) {
        return this.tracking.get(person.getEmail()).stream()
                .filter(o1 -> o1.getHabitName().equals(name))
                .toList();
    }

    @Override
    public List<Tracking> getCurrentTracking(Person person) {
        return tracking.get(person.getEmail()).stream()
                .filter(o1 -> o1.getDate().equals(LocalDate.now()))
                .toList();
    }

    @Override
    public List<Tracking> getAllTracking(Person person) {
        return tracking.get(person.getEmail());
    }
}
