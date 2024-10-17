package aleksey.concoleinterface;

import aleksey.utils.Manager;
import aleksey.model.Person;
import aleksey.model.Tracking;
import aleksey.services.HabitService;
import aleksey.services.PersonService;
import aleksey.services.TrackingService;

public class MainMenu {

    private final PersonService personService = Manager.personService();
    private final HabitService habitService = Manager.habitService();
    private final TrackingService trackingService = Manager.trackingService();

    private String hasNextString() {
        return Manager.nextLine();
    }

    public void start() {
        while (true) {
            printHello();
            String value = hasNextString();
            if (value.equals("1")) {
                Person person = personService.register();
                if (person != null) {
                    personalAccount(person);
                }
            } else if (value.equals("2")) {
                Person person = personService.login();
                if (person != null) {
                    personalAccount(person);
                }
            } else if (value.equals("3")) {
                String password = personService.recoverYourPassword();
                if (password != null) {
                    System.out.println(password);
                }
            } else if (value.equals("4")) {
                return;
            }
        }
    }

    private void printHello() {
        System.out.println("Welcome to the Habit console application, select one of the menu items");
        System.out.println("----/ 1. registration           /----");
        System.out.println("----/ 2. login                  /----");
        System.out.println("----/ 3. recover your password  /----");
        System.out.println("----/ 4. exit                   /----");
    }

    private void personalAccount(Person person) {
        while (true) {
            printPersonalAccount();
            String value = hasNextString();
            if (value.equals("1")) {
                personService.editProfile(person);
            } else if (value.equals("2")) {
                personService.removePerson(person.getEmail());
                return;
            } else if (value.equals("3")) {
                personService.updatePassword(person);
                return;
            } else if (value.equals("4")) {
                personalHabit(person);
            } else if (value.equals("5")) {
                personalTracking(person);
            } else if (value.equals("6")) {
                return;
            }
        }

    }

    private void printPersonalAccount() {
        System.out.println("welcome to your personal account");
        System.out.println("----/ 1. edit a profile                     /----");
        System.out.println("----/ 2. delete a profile                   /----");
        System.out.println("----/ 3. reset your password                /----");
        System.out.println("----/ 4. managing habits                    /----");
        System.out.println("----/ 5. Tracking                           /----");
        System.out.println("----/ 6. exit                               /----");
    }

    public void personalTracking(Person person) {
        while (true) {
            printPersonalTracking();
            String value = hasNextString();
            if (value.equals("1")) {
                Tracking tracking = trackingService.notice(person);
                if (tracking == null) {
                    return;
                }
            } else if (value.equals("2")) {
                trackingService.getAllTrackingAndHabitName(person);
            } else if (value.equals("3")) {
                trackingService.generatedStatistics(person);
            } else if (value.equals("4")) {
                trackingService.getCurrentHabit(person);
            } else if (value.equals("5")) {
                trackingService.progressOfSuccessfulExecution(person);
            } else if (value.equals("6")) {
                trackingService.getAllTracking(person);
            } else if (value.equals("7")) {
                return;
            }
        }
    }

    private void printPersonalTracking() {
        System.out.println("welcome to your personal tracking");
        System.out.println("----/ 1. Mark the fulfillment of a habit.                                              /----");
        System.out.println("----/ 2. A history of performance for each habit.                                      /----");
        System.out.println("----/ 3. Statistics of habit fulfillment for the specified period (day, week, month).  /----");
        System.out.println("----/ 4. Counting the current series of habit fulfillment.                             /----");
        System.out.println("----/ 5. The percentage of successful completion of habits over a certain period.      /----");
        System.out.println("----/ 6. Progress report.                                                              /----");
        System.out.println("----/ 7. Exit.                                                                         /----");
    }

    private void personalHabit(Person person) {
        while (true) {
            printHelloHabit();
            String value = hasNextString();
            if (value.equals("1")) {
                habitService.createHabit(person);
            } else if (value.equals("2")) {
                habitService.removeHabit(person);
            } else if (value.equals("3")) {
                habitService.updateHabit(person);
            } else if (value.equals("4")) {
                habitService.printHabits(person);
            } else if (value.equals("5")) {
                return;
            }
        }
    }

    private void printHelloHabit() {
        System.out.println("Welcome to Habit Management");
        System.out.println("----/ 1. Create a habit   /----");
        System.out.println("----/ 2. remove a habit   /----");
        System.out.println("----/ 3. updating a habit /----");
        System.out.println("----/ 4. view habits      /----");
        System.out.println("----/ 5. exit             /----");

    }

}
