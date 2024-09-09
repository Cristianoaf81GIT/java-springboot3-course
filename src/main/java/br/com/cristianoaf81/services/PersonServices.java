package br.com.cristianoaf81.services;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cristianoaf81.exceptions.ResourceNotFoundException;
import br.com.cristianoaf81.data.vo.v1.PersonVO;
import br.com.cristianoaf81.repositories.PersonRepository;

@Service
public class PersonServices {
    @Autowired
    PersonRepository repository;
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonVO create(PersonVO person) {
        logger.info("Creating a new person");
        // repository.save(person);
        return null;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating person");
        String exceptionMessage = "Record nof found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);

        var entity = repository.findById(person.getId()).orElseThrow(sup);

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        repository.save(entity);
        return null;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        String exceptionMessage = "Record nof found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);
        var entity = repository.findById(id).orElseThrow(sup);
        repository.delete(entity);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one PersonVO");
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return null;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
        repository.findAll().stream();
        return null;
    }

}
