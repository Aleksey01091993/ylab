package aleksey.repository.impl;

import aleksey.model.Person;
import aleksey.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    private final PersonRepository repository = new PersonRepositoryImpl();
    private Person person;

    @BeforeEach
    public void addPerson() {
        Person person = repository.create(new Person("email", "name", "password"));
        this.person = person;
    }

    @Test
    void create() {
        Person person = new Person("emailnew", "name", "password");
        Person save = repository.create(person);
        assertNotNull(save);

    }

    @Test
    void getPassword() {
        String password = repository.getPassword("email");
        assertEquals(password, this.person.getPassword());
    }

    @Test
    void update() {
        repository.update("email", new Person("new", "new", "new"));
        assertNotNull(repository.getPerson("new"));
    }

    @Test
    void getPerson() {
        Person person = repository.getPerson("email");
        assertEquals(person.getName(), "name");
        assertEquals(person.getEmail(), "email");
        assertEquals(person.getPassword(), "password");
    }

    @Test
    void remove() {
        repository.remove("email");
        assertNull(repository.getPerson("email"));
    }
}