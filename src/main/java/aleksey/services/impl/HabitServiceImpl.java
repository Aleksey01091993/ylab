package aleksey.services.impl;

import aleksey.model.Habit;
import aleksey.model.Person;
import aleksey.repository.HabitRepository;
import aleksey.services.HabitService;
import aleksey.utils.Manager;

import java.time.LocalDate;
import java.util.List;

public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    public HabitServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    private String hasNextString() {
        return Manager.nextLine();
    }

    @Override
    public void removePerson(String email) {
        habitRepository.removePerson(email);
    }

    @Override
    public void newPerson(Person person) {
        habitRepository.newPerson(person);
    }


    @Override
    public void createHabit(Person person) {
        System.out.println("Enter a name for the habit: ");
        String name = hasNextString();
        System.out.println("Enter a description for the habit: ");
        String description = hasNextString();
        System.out.println("Enter a frequency the habit: ");
        System.out.println("frequency \n 1. week \n 2. days");
        String frequency = hasNextString();
        while (!(frequency.equals("1") || frequency.equals("2"))) {
            System.out.println("choose one of the options 1 or 2");
            frequency = hasNextString();
        }
        if (frequency.equals("1")) {
            frequency = "week";
        } else if (frequency.equals("2")) {
            frequency = "days";
        }
        Habit habit = habitRepository.createHabit(person.getEmail(), new Habit(LocalDate.now(), description, frequency, name));
        while (habit == null) {
            System.out.println("There is already a habit with this name. Repeat the input.");
            name = hasNextString();
            if (name.equals("0")) {
                return;
            } else {
                habit = habitRepository.createHabit(person.getEmail(), new Habit(LocalDate.now(), description, frequency, name));
            }
        }
    }

    @Override
    public void removeHabit(Person person) {
        System.out.println("enter the name of the habit you want to delete");
        habitRepository.removeHabit(person.getEmail(), hasNextString());
    }

    @Override
    public void updateHabit(Person person) {
        System.out.println("Enter habit name: ");
        Habit habit = habitRepository.getHabit(person.getEmail(), hasNextString());
        if (habit == null) {
            System.out.println("No personal habits found");
        } else {
            System.out.println("----/ 1. Updating a habit name        /----");
            System.out.println("----/ 2. Updating a habit description /----");
            System.out.println("----/ 3. Updating a habit frequency   /----");
        }
        String value = hasNextString();
        if (value.equals("1")) {
            System.out.println("Enter new habit name: ");
            habitRepository.updateHabit(person.getEmail(), habit, hasNextString());
        } else if (value.equals("2")) {
            System.out.println("Enter new habit description: ");
            habit.setDescription(hasNextString());
            habitRepository.updateHabit(person.getEmail(), habit, habit.getName());
        } else if (value.equals("3")) {
            System.out.println("Enter new habit habitFrequency: ");
            System.out.println("habitFrequency: \n 1. week \n 2. days");
            String habitFrequency = hasNextString();
            while (!(habitFrequency.equals("1") || habitFrequency.equals("2"))) {
                System.out.println("choose one of the options 1 or 2");
                habitFrequency = hasNextString();
            }
            if (habitFrequency.equals("1")) {
                habitFrequency = "week";
            } else if (habitFrequency.equals("2")) {
                habitFrequency = "days";
            }
            habit.setFrequency(habitFrequency);
            habitRepository.updateHabit(person.getEmail(), habit, habit.getName());
        }
    }

    @Override
    public void printHabits(Person person) {
        habitRepository.getHabits(person.getEmail()).forEach(System.out::println);
    }

    @Override
    public Habit getHabit(String email, String name) {
        return habitRepository.getHabit(email, name);
    }

    @Override
    public List<String> getHabitsName(String email) {
        return habitRepository.getHabitsName(email);
    }

}
