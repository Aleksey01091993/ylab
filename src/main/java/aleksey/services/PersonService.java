package aleksey.services;

import aleksey.model.Person;

public interface PersonService {
    Person register();

    Person login();

    String recoverYourPassword();

    void editProfile(Person person);

    void removePerson(String email);

    void updatePassword(Person person);
}
