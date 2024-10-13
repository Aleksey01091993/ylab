package aleksey.repository.impl;

import aleksey.model.Habit;
import aleksey.model.Person;
import aleksey.repository.HabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HabitRepositoryImplTest {

    private final HabitRepository repository = new HabitRepositoryImpl();
    private Habit habit;
    private final Person person = new Person("email", "name", "password");


    @BeforeEach
    void setUp() {
        repository.newPerson(person);
        Habit newHabit = repository.createHabit(person.getEmail(), new Habit(LocalDate.now(), "description", "day", "name"));
        habit = newHabit;
    }


    @Test
    void newPerson() {
        assertNotNull(repository.getHabit(person.getEmail(), habit.getName()));
    }

    @Test
    void removePerson() {
        repository.removePerson(person.getEmail());
        assertNull(repository.getHabit(person.getEmail(), habit.getName()));
    }

    @Test
    void createHabit() {
        assertNotNull(repository.getHabit(person.getEmail(), habit.getName()));
    }

    @Test
    void removeHabit() {
        repository.removeHabit(person.getEmail(), habit.getName());
        assertNull(repository.getHabit(person.getEmail(), habit.getName()));
    }

    @Test
    void getHabits() {
        assertEquals(repository.getHabits(person.getEmail()).size(), 1);
    }

    @Test
    void getHabit() {
        assertEquals(repository.getHabit(person.getEmail(), habit.getName()), habit);
    }

    @Test
    void updateHabit() {
        Habit newHabit = new Habit(LocalDate.now(), "new", "new", "name");
        repository.updateHabit(person.getEmail(), newHabit, "new");
        newHabit.setName("new");
        assertEquals(repository.getHabit(person.getEmail(), habit.getName()), newHabit);
    }

    @Test
    void getHabitsName() {
        assertEquals(repository.getHabits(person.getEmail()).size(), 1);
    }
}