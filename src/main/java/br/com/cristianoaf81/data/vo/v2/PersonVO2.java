package br.com.cristianoaf81.data.vo.v2;

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;

public class PersonVO2 implements Serializable {

    private static final long serialVersionUID = -6255103960109907768L;

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    private Date birthDay;

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

    public Date getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDate(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonVO2 p = (PersonVO2) o;
        return id == p.id && Objects.equals(firstName, p.firstName) && Objects.equals(lastName, p.lastName)
                && Objects.equals(address, p.address) && Objects.equals(gender, p.gender)
                && Objects.equals(birthDay, p.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, birthDay);
    }

    @Override
    public String toString() {
        String template = "Person [ id = %s ,firstName= %s, lastName = %s , gender = %s, birthDate = %s ]";
        return String.format(template, id, firstName, lastName, gender, birthDay);
    }

}
