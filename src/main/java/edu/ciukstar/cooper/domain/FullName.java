package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Embeddable
public class FullName implements Serializable {

    public static FullNameBuilder.Surname from() {
        return FullNameBuilder.from();
    }
    
    public static FullName from(String surname, String name, String patronymic) {
        return new FullName(surname, name, patronymic);
    }

    static FullName empty() {
        return FullName.from(null, null, null);
    }

    private FullName(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public FullName() {
        this(null, null, null);
    }

    @Override
    public String toString() {
        return Arrays.asList(getSurname(), getName(), getPatronymic()).stream()
                .filter(e -> e != null).collect(Collectors.joining(" "));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.surname);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.patronymic);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FullName other = (FullName) obj;
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.patronymic, other.patronymic)) {
            return false;
        }
        return true;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    
    @NotBlank(message = "{Surname_may_not_be_blank}")
    @Column(name = "surname")
    private String surname;    
    @NotBlank(message = "{Name_may_not_be_blank}")
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
}
