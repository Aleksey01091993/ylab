package aleksey.getimpstatic;

import aleksey.repository.HabitRepository;
import aleksey.repository.PersonRepository;
import aleksey.repository.TrackingRepository;
import aleksey.repository.impl.HabitRepositoryImpl;
import aleksey.repository.impl.PersonRepositoryImpl;
import aleksey.repository.impl.TrackingRepositoryImpl;
import aleksey.services.HabitService;
import aleksey.services.PersonService;
import aleksey.services.TrackingService;
import aleksey.services.impl.HabitServiceImpl;
import aleksey.services.impl.PersonServiceImpl;
import aleksey.services.impl.TrackingServiceImpl;

import java.util.Scanner;

public class Manager {
    private final static HabitRepositoryImpl habitRepo = new HabitRepositoryImpl();
    private final static PersonRepositoryImpl personRepo = new PersonRepositoryImpl();
    private final static TrackingRepositoryImpl trackingRepo = new TrackingRepositoryImpl();
    private final static Scanner scanner = new Scanner(System.in);

    public static String nextLine() {
        return scanner.nextLine();
    }

    public static HabitRepository isMemoryHabitRepository() {
        return habitRepo;
    }

    public static PersonRepository isMemoryPersonRepository() {
        return personRepo;
    }

    public static TrackingRepository isMemoryTrackingRepository() {
        return trackingRepo;
    }

    public static HabitService habitService() {
        return new HabitServiceImpl();
    }

    public static PersonService personService() {
        return new PersonServiceImpl();
    }

    public static TrackingService trackingService() {
        return new TrackingServiceImpl();
    }
}
