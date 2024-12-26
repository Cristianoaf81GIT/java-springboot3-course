package br.com.cristianoaf81.data.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "author", "launchDate", "price", "title" })
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

  private static final long serialVersionUID = -4549624971287986532L;

  @JsonProperty("id")
  private Long key;
  private String author;
  private Date launchDate;
  private Double price;
  private String title;

  public BookVO() {
  }

  public Long getKey() {
    return this.key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getLaunchDate() {
    return this.launchDate;
  }

  public void setLaunchDate(Date launchDate) {
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
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookVO b = (BookVO) o;
    return key == b.key &&
        Objects.equals(author, b.author) &&
        launchDate.equals(b.launchDate) &&
        Objects.equals(price, b.price) &&
        Objects.equals(title, b.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, author, launchDate, price, title);
  }

  @Override
  public String toString() {
    String template = "BookVO { key = %s, author = %s, launchDate = %s, price = %s, title = %s }";
    return String.format(template, key, author, launchDate, price, title);
  }
}
