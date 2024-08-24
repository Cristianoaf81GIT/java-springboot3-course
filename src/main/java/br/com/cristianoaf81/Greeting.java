package br.com.cristianoaf81;

import java.util.Objects;

public class Greeting implements java.io.Serializable {
  public static final long serialVersionUID = -4027845199484141794L;
  private final Long id;
  private final String content;

  public Greeting(Long id, String content) {
    this.id = id;
    this.content = content;
  }

  public Long getId() {
    return this.id;
  }

  public String getContent() {
    return this.content;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Greeting greeting = (Greeting) obj;
    return id == greeting.id && Objects.equals(content, greeting.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id,content);
  }

  @Override
  public String toString() {
    String template = "Greeting{ id = %l, content = %s }";
    return String.format(template, id,content);
  }

}

