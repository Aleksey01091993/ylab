package aleksey.utils;

import aleksey.concoleinterface.MainMenu;
import aleksey.repository.HabitRepository;
import aleksey.repository.PersonRepository;
import aleksey.repository.TrackingRepository;
import aleksey.repository.impl.HabitRepositoryImpl;
import aleksey.repository.impl.PersonRepositoryImpl;
import aleksey.repository.impl.TrackingRepositoryImpl;
import aleksey.repository.implDB.HabitRepositoryDB;
import aleksey.repository.implDB.PersonRepositoryDB;
import aleksey.repository.implDB.TrackingRepositoryDB;
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

    private Manager() {
    }

    public static String nextLine() {
        return scanner.nextLine();
    }

    private static HabitRepository inMemoryHabitRepository() {
        return habitRepo;
    }

    private static PersonRepository inMemoryPersonRepository() {
        return personRepo;
    }

    private static TrackingRepository inMemoryTrackingRepository() {
        return trackingRepo;
    }

    private static HabitRepository inDBHabitRepository() {
        return HabitRepositoryDB.getInstance();
    }

    private static PersonRepository inDBPersonRepository() {
        return PersonRepositoryDB.getInstance();
    }

    private static TrackingRepository inDBTrackingRepository() {
        return TrackingRepositoryDB.getInstance();
    }

    private static HabitService inMemoryHabitService() {
        return new HabitServiceImpl(inMemoryHabitRepository());
    }

    private static PersonService inMemoryPersonService() {
        return new PersonServiceImpl(inMemoryHabitService(), inMemoryTrackingService(), inMemoryPersonRepository());
    }

    private static TrackingService inMemoryTrackingService() {
        return new TrackingServiceImpl(inMemoryHabitService(), inMemoryTrackingRepository());
    }

    private static HabitService inDBhabitService() {
        return new HabitServiceImpl(inDBHabitRepository());
    }

    private static PersonService inDBpersonService() {
        return new PersonServiceImpl(inDBhabitService(), inDBtrackingService(), inDBPersonRepository());
    }

    private static TrackingService inDBtrackingService() {
        return new TrackingServiceImpl(inDBhabitService(), inDBTrackingRepository());
    }

    public static MainMenu inMemory() {
        return new MainMenu(inMemoryHabitService(), inMemoryPersonService(), inMemoryTrackingService());
    }

    public static MainMenu inDB() {
        return new MainMenu(inDBhabitService(), inDBpersonService(), inDBtrackingService());
    }
}
