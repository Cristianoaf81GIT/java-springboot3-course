package br.com.cristianoaf81.services;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import br.com.cristianoaf81.exceptions.RequiredObjectsIsNullException;
import br.com.cristianoaf81.exceptions.ResourceNotFoundException;
import br.com.cristianoaf81.mapper.ClassMapper;
import br.com.cristianoaf81.mapper.ClassMapperCustom;
import br.com.cristianoaf81.model.Book;
import br.com.cristianoaf81.controllers.BookController;
import br.com.cristianoaf81.data.vo.v1.BookVO;
import br.com.cristianoaf81.repositories.BookRepository;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;

    @Autowired
    ClassMapperCustom mapper;

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    public BookVO create(BookVO book) {
        if (book == null) {
            throw new RequiredObjectsIsNullException();
        }
        logger.info("Creating a new book");
        var entity = ClassMapper.parseObject(book, Book.class);
        var saved = repository.save(entity);
        BookVO vo = ClassMapper.parseObject(saved, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book) {
        if (book == null) {
            throw new RequiredObjectsIsNullException();
        }
        logger.info("Updating book");
        String exceptionMessage = "Record nof found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);
        var entity = repository.findById(book.getKey()).orElseThrow(sup);
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        var saved = repository.save(entity);
        BookVO vo = ClassMapper.parseObject(saved, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one book");
        String exceptionMessage = "Record not found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);
        var entity = repository.findById(id).orElseThrow(sup);
        repository.delete(entity);
    }

    public BookVO findById(Long id) {
        logger.info("Finding one BookVO");
        String exceptionMessage = "No records found for this id";
        Supplier<ResourceNotFoundException> sup = () -> new ResourceNotFoundException(exceptionMessage);
        var entity = repository.findById(id).orElseThrow(sup);
        BookVO vo = ClassMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<BookVO> findAll() {
        logger.info("Finding all book");
        List<BookVO> books =  ClassMapper.parseListObjects(repository.findAll(), BookVO.class);
        Consumer<BookVO> bookConsumer = (bookVo) -> {
            bookVo.add(linkTo(methodOn(BookController.class).findById(bookVo.getKey())).withSelfRel());
        };
        books.stream().forEach(bookConsumer);
        return books;    
    }

}
