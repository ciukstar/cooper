package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Embeddable
public class FullName implements Serializable {

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
