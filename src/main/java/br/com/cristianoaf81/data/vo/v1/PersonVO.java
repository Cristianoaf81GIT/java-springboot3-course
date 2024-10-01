package br.com.cristianoaf81.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.hateoas.RepresentationModel;
// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "first_name", "last_name", "address", "gender" })
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

    private static final long serialVersionUID = -6255103960109907768L;
    @JsonProperty("id")
    private Long key;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    // @JsonIgnore()
    private String gender;

    public Long getKey() {
        return this.key;
    }

    public void setKey(Long key) {
        this.key = key;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonVO p = (PersonVO) o;
        return key == p.key && Objects.equals(firstName, p.firstName) && Objects.equals(lastName, p.lastName)
                && Objects.equals(address, p.address) && Objects.equals(gender, p.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, firstName, lastName, gender);
    }

    @Override
    public String toString() {
        String template = "Person { key = %s ,firstName= %s, lastName = %s , gender = %s}";
        return String.format(template, key, firstName, lastName, gender);
    }

}
