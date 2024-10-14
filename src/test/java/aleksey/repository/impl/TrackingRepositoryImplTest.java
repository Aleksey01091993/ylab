package aleksey.repository.impl;

import aleksey.model.Person;
import aleksey.model.Tracking;
import aleksey.repository.TrackingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackingRepositoryImplTest {

    private TrackingRepository repository = new TrackingRepositoryImpl();
    private Person person;
    private Tracking tracking;


    @BeforeEach
    void setUp() {
        person = new Person("email", "name", "password");
        tracking = new Tracking("habit", "email");
        repository.newPerson(person);
        repository.add(person, tracking);
    }

    @AfterEach
    void cleanUp() {
        repository = new TrackingRepositoryImpl();
    }

    @Test
    void newPerson() {
        repository.newPerson(person);
        assertNotNull(repository.getAllTracking(person));
    }

    @Test
    void removePerson() {
        repository.removePerson(person.getEmail());
        assertNull(repository.getAllTracking(person));
    }

    @Test
    void getStatistic() {
    }

    @Test
    void add() {
        assertNotNull(repository.getAllTracking(person).get(0));
        assertEquals(tracking, repository.getAllTracking(person).get(0));
    }

    @Test
    void getTrackingAndHabitName() {
        assertNotNull(repository.getTrackingAndHabitName(person, "habit").get(0));
        assertEquals(tracking, repository.getTrackingAndHabitName(person, "habit").get(0));
    }

    @Test
    void getCurrentTracking() {
        assertNotNull(repository.getCurrentTracking(person).get(0));
        assertEquals(tracking, repository.getCurrentTracking(person).get(0));
    }

    @Test
    void getAllTracking() {
        assertNotNull(repository.getAllTracking(person).get(0));
        assertEquals(tracking, repository.getAllTracking(person).get(0));
    }
}