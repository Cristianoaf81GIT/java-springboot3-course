package br.com.cristianoaf81.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.cristianoaf81.data.vo.v1.BookVO;
import br.com.cristianoaf81.exceptions.RequiredObjectsIsNullException;
import br.com.cristianoaf81.model.Book;
import br.com.cristianoaf81.repositories.BookRepository;
import br.com.cristianoaf81.services.BookServices;
import br.com.cristianoaf81.unittests.mapper.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    public void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
    }

    @Test
    void testFindAll() {
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        var book = service.findAll();
        assertNotNull(book);
        assertEquals(14, book.size());

        var bookOne = book.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());
        assertTrue(bookOne.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));

    }

    @Test
    void testCreate() {
        Book entity = input.mockEntity(1);
        Book persisted = new Book();
        persisted.setId(1L);
        persisted.setTitle(entity.getTitle());
        persisted.setAuthor(entity.getAuthor());
        persisted.setPrice(entity.getPrice());
        persisted.setLaunchDate(entity.getLaunchDate());

        BookVO vo = input.mockVO(1);
        vo.setAuthor(entity.getAuthor());
        vo.setKey(persisted.getId());
        vo.setLaunchDate(entity.getLaunchDate());
        vo.setPrice(entity.getPrice());
        when(repository.save(entity)).thenReturn(persisted);
        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
    }

    @Test
    void testCreateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectsIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It\'s not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
    }

    @Test
    void testUpdateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectsIsNullException.class, () -> {
            service.update(null);
        });
        String expectedMessage = "It\'s not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.findById(1L);
        service.delete(1L);
    }

    public void tearDown() {
        Mockito.reset(service, repository);
    }

}
