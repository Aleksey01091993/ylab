package aleksey.services;

import aleksey.model.Habit;
import aleksey.model.Person;

import java.util.List;

public interface HabitService {
    void removePerson(String email);

    void newPerson(Person person);

    void createHabit(Person person);

    void removeHabit(Person person);

    void updateHabit(Person person);

    void printHabits(Person person);

    Habit getHabit(String email, String name);

    List<String> getHabitsName(String email);
}
