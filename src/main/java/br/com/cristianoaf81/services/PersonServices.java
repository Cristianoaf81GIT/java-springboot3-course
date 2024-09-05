package br.com.cristianoaf81.services;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cristianoaf81.exceptions.ResourceNotFoundException;
import br.com.cristianoaf81.model.Person;
import br.com.cristianoaf81.repositories.PersonRepository;

@Service
public class PersonServices {
    @Autowired
    PersonRepository repository;
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person) {
        logger.info("Creating a new person");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating person");
        String exceptionMessage = "Record nof found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);

        var entity = repository.findById(person.getId()).orElseThrow(sup);

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        String exceptionMessage = "Record nof found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);
        var entity = repository.findById(id).orElseThrow(sup);
        repository.delete(entity);
    }

    public Person findById(Long id) {
        logger.info("Finding one Person");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public List<Person> findAll() {
        logger.info("Finding all people");
        return repository.findAll();
    }

}
