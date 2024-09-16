package br.com.cristianoaf81.mapper;

import java.util.Date;
import org.springframework.stereotype.Service;
import br.com.cristianoaf81.data.vo.v2.PersonVO2;
import br.com.cristianoaf81.model.Person;

@Service
public class ClassMapperCustom {

    public PersonVO2 convertEntityToVo(Person person) {
        PersonVO2 vo = new PersonVO2();
        vo.setId(person.getId());
        vo.setGender(person.getGender());
        vo.setAddress(person.getAddress());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setBirthDate(new Date());
        return vo;
    }

    public Person convertVotoEntity(PersonVO2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        return entity;
    }

}
