package br.com.cristianoaf81.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import br.com.cristianoaf81.model.Person;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person) {
        logger.info("Creating a new person");
        person.setId(counter.incrementAndGet());
        return person;
    }

    public Person update(Person person) {
        logger.info("Updating person");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting one person");
    }

    public Person findById(String id) {
        logger.info("Finding one Person");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Leandro");
        person.setLastName("Costa");
        person.setAddress("Uberlândia-MG");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all people");
        List<Person> persons = new ArrayList<>();
        IntStream.range(0, 8).forEach(fakeId -> {
            Person p = mockPerson(fakeId);
            persons.add(p);
        });
        return persons;
    }

    private Person mockPerson(int fakeId) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person " + fakeId);
        person.setLastName("Last name " + fakeId);
        person.setAddress("Somewhere in Brasil " + fakeId);
        person.setGender("Male");
        return person;
    }

}
