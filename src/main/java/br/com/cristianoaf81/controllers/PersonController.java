package br.com.cristianoaf81.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import br.com.cristianoaf81.data.vo.v1.PersonVO;
import br.com.cristianoaf81.data.vo.v2.PersonVO2;
import br.com.cristianoaf81.services.PersonServices;
import br.com.cristianoaf81.utils.MediaType;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML })
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return this.service.findById(id);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<PersonVO> findAll() {
        return this.service.findAll();
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML })
    public PersonVO create(@RequestBody() PersonVO person) {
        return this.service.create(person);
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML })
    public PersonVO update(@RequestBody() PersonVO person) {
        return this.service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            "application/x-yaml" }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML })
    public PersonVO2 createv2(@RequestBody() PersonVO2 person) {
        return this.service.createv2(person);
    }
}
