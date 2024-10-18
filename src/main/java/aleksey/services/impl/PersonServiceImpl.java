package aleksey.services.impl;

import aleksey.model.Person;
import aleksey.repository.PersonRepository;
import aleksey.services.HabitService;
import aleksey.services.PersonService;
import aleksey.services.TrackingService;
import aleksey.utils.Manager;

public class PersonServiceImpl implements PersonService {

    private final TrackingService trackingService;
    private final HabitService habitService;
    private final PersonRepository personRepository;

    public PersonServiceImpl(HabitService habitService, TrackingService trackingService, PersonRepository personRepository) {
        this.habitService = habitService;
        this.trackingService = trackingService;
        this.personRepository = personRepository;
    }

    private String hasNextString() {
        return Manager.nextLine();
    }

    @Override
    public Person register() {
        System.out.println("You have chosen registration please follow the instructions on the screen");
        System.out.println("Enter your name: ");
        String name = hasNextString();
        System.out.println("Enter your email: ");
        String email = hasNextString();
        System.out.println("Enter your password: ");
        String password = hasNextString();
        Person person = personRepository.create(new Person(name, email, password));
        while (person == null) {
            System.out.println("the user with this email has already been registered if you do not " +
                    "remember the password, you can restore it by going to the main menu to do this," + " select \"0\" or enter another email");
            String newEmail = hasNextString();
            if (newEmail.equals("0")) {
                return null;
            }
            person = personRepository.create(new Person(name, newEmail, password));
        }
        trackingService.newPerson(person);
        habitService.newPerson(person);
        return person;
    }

    @Override
    public Person login() {
        System.out.println("Enter your email: ");
        String email = hasNextString();
        Person person = personRepository.getPerson(email);
        while (person == null) {
            System.out.println("The user with this email has not been found, you can use the registration," +
                    " to do this, press \"0\" and select registration in the main menu, or repeat the input.");
            String value = hasNextString();
            if (value.equals("0")) {
                return null;
            } else {
                person = personRepository.getPerson(value);
            }
        }
        System.out.println("Enter your password: ");
        String password = hasNextString();
        while (!person.getPassword().equals(password)) {
            System.out.println("You entered the wrong password, repeat the input or press \"0\" to" +
                    " go to the main menu and restore the password.");
            String value = hasNextString();
            if (value.equals("0")) {
                return null;
            }
            password = value;
        }
        return person;
    }

    @Override
    public String recoverYourPassword() {
        System.out.println("Enter your email: ");
        String password = personRepository.getPassword(hasNextString());
        while (password == null) {
            System.out.println("There is no user with such an email address, you can register," +
                    " to do this, enter \"0\" to exit the main menu or repeat the input.");
            password = personRepository.getPassword(hasNextString());
            if (password.equals("0")) {
                return null;
            }
        }
        return "Your password: " + password;
    }

    @Override
    public void editProfile(Person person) {
        System.out.println("----/ 1. change the name          /----");
        System.out.println("----/ 2. change the email address /----");
        String value = hasNextString();
        if (value.equals("1")) {
            System.out.println("Enter a new name:");
            String name = hasNextString();
            person.setName(name);
            personRepository.update(person.getEmail(), person);
            System.out.println("The name has been successfully changed.");
        } else if (value.equals("2")) {
            System.out.println("Enter a new email:");
            String newEmail = hasNextString();
            String oldEmail = person.getEmail();
            person.setEmail(newEmail);
            while (personRepository.update(oldEmail, person) == null) {
                System.out.println("a user with this email already exists, enter another email or press \"0\" to go" +
                        " to the previous menu");
                newEmail = hasNextString();
                if (newEmail.equals("0")) {
                    return;
                }
                person.setEmail(newEmail);
            }
        }
    }

    @Override
    public void removePerson(String email) {
        trackingService.removePerson(email);
        habitService.removePerson(email);
        personRepository.remove(email);
    }

    @Override
    public void updatePassword(Person person) {
        System.out.println("enter a new password: ");
        person.setPassword(hasNextString());
        personRepository.update(person.getEmail(), person);
        System.out.println("The password has been successfully changed.");
    }
}
