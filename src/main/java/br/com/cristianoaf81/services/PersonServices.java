package br.com.cristianoaf81.services;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import br.com.cristianoaf81.exceptions.ResourceNotFoundException;
import br.com.cristianoaf81.mapper.ClassMapper;
import br.com.cristianoaf81.mapper.ClassMapperCustom;
import br.com.cristianoaf81.model.Person;
import br.com.cristianoaf81.data.vo.v1.PersonVO;
import br.com.cristianoaf81.data.vo.v2.PersonVO2;
import br.com.cristianoaf81.repositories.PersonRepository;
import br.com.cristianoaf81.controllers.PersonController;

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
        var saved = repository.save(entity);
        PersonVO vo = ClassMapper.parseObject(saved, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating person");
        String exceptionMessage = "Record nof found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);
        var entity = repository.findById(person.getKey()).orElseThrow(sup);
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var saved = repository.save(entity);
        PersonVO vo = ClassMapper.parseObject(saved, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
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
        PersonVO vo = ClassMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
        List<PersonVO> persons =  ClassMapper.parseListObjects(repository.findAll(), PersonVO.class);
        Consumer<PersonVO> personConsumer = (personVo) -> {
            personVo.add(linkTo(methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
        };
        persons.stream().forEach(personConsumer);
        return persons;
    }

    public PersonVO2 createv2(PersonVO2 person) {
        logger.info("Creating a new person v2");
        var entity = mapper.convertVotoEntity(person);
        var vo = repository.save(entity);
        return mapper.convertEntityToVo(vo);
    }

}
