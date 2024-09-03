package br.com.cristianoaf81.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.cristianoaf81.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
