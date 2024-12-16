package br.com.cristianoaf81.unittests.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import br.com.cristianoaf81.data.vo.v1.BookVO;
import br.com.cristianoaf81.model.Book;

public class MockBook {

  public Book mockEntity() {
    return mockEntity(0);
  }

  public BookVO mockVO() {
    return mockVO(0);
  }

  public List<BookVO> mockVOList() {
    List<BookVO> list = new ArrayList<BookVO>();
    IntStream.range(0, 14).forEach(n -> {
      list.add(mockVO(n));
    });
    return list;
  }

  public List<Book> mockEntityList() {
    List<Book> books = new ArrayList<Book>();
    IntConsumer action = (n) -> {
      books.add(mockEntity(n));
    };
    IntStream.range(0, 14).forEach(action);
    return books;
  }

  public Book mockEntity(Integer number) {
    Book book = new Book();
    book.setId(number.longValue());
    book.setAuthor("Some author" + number);
    book.setLaunchDate(LocalDateTime.now());
    book.setTitle("Some Title" + number);
    book.setPrice(25D);
    return book;
  }

  public BookVO mockVO(Integer number) {
    BookVO book = new BookVO();
    book.setKey(number.longValue());
    book.setAuthor("Some Author" + number);
    book.setLaunchDate(LocalDateTime.now());
    book.setPrice(25D);
    book.setTitle("Some Title" + number);
    return book;
  }

}
