package br.com.cristianoaf81.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="books")
public class Book implements Serializable {

  private static final long serialVersionUID = 9133080550416537698L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "author", nullable = false, length = 180)
  private String author;

  @Column(name = "launch_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP) // necessário para campos de data
  private LocalDateTime launchDate;

  @Column(name = "price", nullable = false)
  private Double price;

  @Column(name = "title", nullable = false)
  private String title;


  public Book() {}


  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDateTime getLaunchDate() {
    return this.launchDate;
  }

  public void setLaunchDate(LocalDateTime launchDate) {
    this.launchDate = launchDate;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }
    Book b = (Book) o;
    return id == b.id && 
      Objects.equals(author, b.author) && 
      launchDate.isEqual(b.launchDate) &&
      Objects.equals(price, b.price) &&
      Objects.equals(title, b.title);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, author, launchDate, price, title);
  }

  @Override
  public String toString() {
    String template = "Book { id = %s, author = %s, launchDate = %s, price = %s, title = %s }";
    return String.format(template, id, author, launchDate, price, title);
  }
}
