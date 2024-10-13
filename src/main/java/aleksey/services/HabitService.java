package aleksey.services;

import aleksey.model.Person;

public interface HabitService {
    void removePerson(String email);

    void newPerson(Person person);

    void createHabit(Person person);

    void removeHabit(Person person);

    void updateHabit(Person person);

    void printHabits(Person person);
}
