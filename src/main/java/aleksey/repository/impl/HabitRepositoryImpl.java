package aleksey.repository.impl;

import aleksey.model.Habit;
import aleksey.model.Person;
import aleksey.repository.HabitRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HabitRepositoryImpl implements HabitRepository {

    private final Map<String, Map<String, Habit>> habits = new HashMap<>();

    @Override
    public void newPerson(Person person) {
        habits.put(person.getEmail(), new HashMap<>());
    }

    @Override
    public void removePerson(String email) {
        habits.remove(email);
    }

    @Override
    public Habit createHabit(String personEmail, Habit habit) {
        if (habits.get(personEmail).containsKey(habit.getName())) {
            return null;
        } else {
            habits.get(personEmail).put(habit.getName(), habit);
        }
        return habit;
    }

    @Override
    public void removeHabit(String personEmail, String habitName) {
        habits.get(personEmail).remove(habitName);
    }

    @Override
    public List<Habit> getHabits(String personEmail) {
        if (!habits.containsKey(personEmail)) {
            return null;
        } else {
            return habits.get(personEmail).values().stream().toList();
        }
    }

    @Override
    public Habit getHabit(String personEmail, String habitName) {
        if (!habits.containsKey(personEmail)) {
            return null;
        } else {
            return habits.get(personEmail).get(habitName);
        }
    }

    @Override
    public Habit updateHabit(String personEmail, Habit habit, String habitNewName) {
        if (!habits.containsKey(personEmail)) {
            return null;
        } else {
            if (!habits.get(personEmail).containsKey(habit.getName())) {
                return null;
            }
            Habit oldHabit = habits.get(personEmail).get(habit.getName());
            if (habit.getName() != null) {
                oldHabit.setName(habitNewName);
                habits.get(personEmail).remove(habit.getName());
            }
            if (habit.getDescription() != null) {
                oldHabit.setDescription(habit.getDescription());
            }
            if (habit.getFrequency() != null) {
                oldHabit.setFrequency(habit.getFrequency());
            }
            return habits.get(personEmail).put(oldHabit.getName(), oldHabit);
        }
    }

    @Override
    public List<String> getHabitsName(String personEmail) {
        if (!habits.containsKey(personEmail)) {
            return null;
        } else {
            return habits.get(personEmail).keySet().stream().toList();
        }
    }

}
