package br.com.cristianoaf81.services;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cristianoaf81.exceptions.ResourceNotFoundException;
import br.com.cristianoaf81.mapper.ClassMapper;
import br.com.cristianoaf81.mapper.ClassMapperCustom;
import br.com.cristianoaf81.model.Person;
import br.com.cristianoaf81.data.vo.v1.PersonVO;
import br.com.cristianoaf81.data.vo.v2.PersonVO2;
import br.com.cristianoaf81.repositories.PersonRepository;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    @Autowired
    ClassMapperCustom mapper;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonVO create(PersonVO person) {
        logger.info("Creating a new person");
        var entity = ClassMapper.parseObject(person, Person.class);
        var vo = repository.save(entity);
        return ClassMapper.parseObject(vo, PersonVO.class);
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
        var vo = repository.save(entity);
        return ClassMapper.parseObject(vo, PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        String exceptionMessage = "Record not found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);
        var entity = repository.findById(id).orElseThrow(sup);
        repository.delete(entity);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one PersonVO");
        String exceptionMessage = "No records found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);
        var entity = repository.findById(id).orElseThrow(sup);
        return ClassMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
        return ClassMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO2 createv2(PersonVO2 person) {
        logger.info("Creating a new person v2");
        var entity = mapper.convertVotoEntity(person);
        var vo = repository.save(entity);
        return mapper.convertEntityToVo(vo);
    }

}
