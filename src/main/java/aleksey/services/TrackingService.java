package aleksey.services;

import aleksey.model.Person;
import aleksey.model.Tracking;

public interface TrackingService {
    void newPerson(Person person);

    void removePerson(String email);

    Tracking notice(Person person);

    void getAllTrackingAndHabitName(Person person);

    void generatedStatistics(Person person);

    void getCurrentHabit(Person person);

    void progressOfSuccessfulExecution(Person person);

    void getAllTracking(Person person);
}
