package aleksey.repository;

import aleksey.model.Person;

public interface PersonRepository {
    Person create(Person person);

    String getPassword(String email);

    Person update(String email, Person person);

    Person getPerson(String email);

    void remove(String email);
}
