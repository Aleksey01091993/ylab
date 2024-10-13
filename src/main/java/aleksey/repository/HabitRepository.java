package aleksey.repository;

import aleksey.model.Habit;
import aleksey.model.Person;

import java.util.List;

public interface HabitRepository {
    void newPerson(Person person);

    void removePerson(String email);

    Habit createHabit(String personEmail, Habit habit);

    void removeHabit(String personEmail, String habitName);

    List<Habit> getHabits(String personEmail);

    Habit getHabit(String personEmail, String habitName);

    Habit updateHabit(String personEmail, Habit habit, String habitNewName);

    List<String> getHabitsName(String personEmail);
}
