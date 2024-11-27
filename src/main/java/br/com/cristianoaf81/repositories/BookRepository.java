package br.com.cristianoaf81.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.cristianoaf81.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> { }
