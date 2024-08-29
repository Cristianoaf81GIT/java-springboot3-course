package br.com.cristianoaf81.model;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private static final long serialVersionUID = -6137977658643654688L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person p = (Person) o;
        return id == p.id && Objects.equals(firstName, p.firstName) && Objects.equals(lastName, p.lastName)
                && Objects.equals(address, p.address) && Objects.equals(gender, p.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender);
    }

    @Override
    public String toString() {
        String template = "Person { id = %s ,firstName= %s, lastName = %s , gender = %s}";
        return String.format(template, id, firstName, lastName, gender);
    }
}
