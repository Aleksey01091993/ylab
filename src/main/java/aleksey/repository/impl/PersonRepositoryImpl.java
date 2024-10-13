package aleksey.repository.impl;

import aleksey.model.Person;
import aleksey.repository.PersonRepository;

import java.util.HashMap;
import java.util.Map;

public class PersonRepositoryImpl implements PersonRepository {

    private final Map<String, Person> persons = new HashMap<>();

    @Override
    public Person create(Person person) {
        String email = person.getEmail();
        if (persons.containsKey(email)) {
            return null;
        } else {
            persons.put(email, person);
        }
        return person;
    }

    @Override
    public String getPassword(String email) {
        return persons.get(email).getPassword();
    }

    @Override
    public Person update(String email, Person person) {
        if (!persons.containsKey(email)) {
            return null;
        } else {
            Person oldPerson = persons.get(email);
            if (person.getName() != null) {
                oldPerson.setName(person.getName());
            }
            if (person.getEmail() != null) {
                if (persons.containsKey(person.getEmail())) {
                    return null;
                } else {
                    oldPerson.setEmail(person.getEmail());
                    persons.remove(email);
                }
            }
            if (person.getPassword() != null) {
                oldPerson.setPassword(person.getPassword());
            }
            persons.put(oldPerson.getEmail(), oldPerson);
            return oldPerson;
        }
    }

    @Override
    public Person getPerson(String email) {
        return persons.get(email);
    }

    @Override
    public void remove(String email) {
        persons.remove(email);
    }
}
