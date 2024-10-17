package aleksey.services.impl;

import aleksey.utils.Manager;
import aleksey.model.Person;
import aleksey.model.Tracking;
import aleksey.services.TrackingService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class TrackingServiceImpl implements TrackingService {

    private String hasNextString() {
        return Manager.nextLine();
    }

    @Override
    public void newPerson(Person person) {
        Manager.isMemoryTrackingRepository().newPerson(person);
    }

    @Override
    public void removePerson(String email) {
        Manager.isMemoryTrackingRepository().removePerson(email);
    }

    @Override
    public Tracking notice(Person person) {
        System.out.println("Enter the name of the habit for which you want to mark the completion");
        String name = hasNextString();
        while (isHabit(person, name)) {
            System.out.println("You have not found this habit, you can press \"0\" to complete it and go to habits" +
                    " management to create it or repeat the input.");
            name = hasNextString();
            if (name.equals("0")) {
                return null;
            }
        }
        Tracking tracking = new Tracking(person.getName(), name);
        Manager.isMemoryTrackingRepository().add(person, tracking);
        System.out.println("the mark is marked");
        return tracking;
    }

    @Override
    public void getAllTrackingAndHabitName(Person person) {
        System.out.println("Enter the name of the habit for which you want to view the progress history.");
        String name = hasNextString();
        List<Tracking> trackings = Manager.isMemoryTrackingRepository().getTrackingAndHabitName(person, name);
        if (trackings.isEmpty()) {
            System.out.println("The execution history was not found.");
        } else {
            trackings.forEach(System.out::println);
        }
    }

    @Override
    public void generatedStatistics(Person person) {
        System.out.println("Enter the period for which you want to generate statistics in" +
                " the format yyyy-mm-dd:yyyy-mm-dd.");
        String period = hasNextString();
        LocalDate start = LocalDate.parse(period.split(":")[0]);
        LocalDate end = LocalDate.parse(period.split(":")[1]);
        while (start.isAfter(end)) {
            System.out.println("The start date cannot be later than the end date. Repeat the input or press \"0\" to exit.");
            period = hasNextString();
            if (period.equals("0")) {
                return;
            }
            start = LocalDate.parse(period.split(":")[0]);
            end = LocalDate.parse(period.split(":")[1]);
        }
        List<Tracking> statistics = Manager.isMemoryTrackingRepository().getStatistic(person, start, end);
        if (statistics.isEmpty()) {
            System.out.println("The execution history was not found.");
        } else {
            statistics.forEach(System.out::println);
        }
    }

    @Override
    public void getCurrentHabit(Person person) {
        List<Tracking> currentHabit = Manager.isMemoryTrackingRepository().getCurrentTracking(person);
        if (currentHabit.isEmpty()) {
            System.out.println("The execution history was not found.");
        } else {
            currentHabit.forEach(System.out::println);
        }
    }

    @Override
    public void progressOfSuccessfulExecution(Person person) {
        System.out.println("Enter the period for which you want to get the percentage of successful completion," +
                " in the format yyyy-mm-dd:yyyy-mm-dd.");
        String period = hasNextString();
        LocalDate start = LocalDate.parse(period.split(":")[0]);
        LocalDate end = LocalDate.parse(period.split(":")[1]);
        while (start.isAfter(end)) {
            System.out.println("The start date cannot be later than the end date. Repeat the input or press \"0\" to exit.");
            period = hasNextString();
            if (period.equals("0")) {
                return;
            }
            start = LocalDate.parse(period.split(":")[0]);
            end = LocalDate.parse(period.split(":")[1]);
        }
        List<Tracking> statistics = Manager.isMemoryTrackingRepository().getStatistic(person, start, end);
        if (statistics.isEmpty()) {
            System.out.println("The execution history was not found.");
        } else {
            List<String> habits = Manager.isMemoryHabitRepository().getHabitsName(person.getEmail());
            for (String habit : habits) {
                System.out.println("The statistics for this stage according to the habit of " +
                        habit +
                        " were: " +
                        100 / Period.between(start, end).getDays() * statistics.stream().filter(o1 -> o1.getHabitName().equals(habit)).count()
                        + "%"
                );
            }
        }
    }

    @Override
    public void getAllTracking(Person person) {
        Manager.isMemoryTrackingRepository().getAllTracking(person).forEach(System.out::println);
    }

    private Boolean isHabit(Person person, String habitName) {
        return Manager.isMemoryHabitRepository().getHabit(person.getEmail(), habitName) == null;
    }

}
