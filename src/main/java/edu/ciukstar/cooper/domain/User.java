package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"})})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{Username_may_not_be_blank}")
    private String username;

    @NotBlank(message = "{Password_may_not_be_blank}")
    private String password;

    @Embedded
    private FullName fullName;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;

    @Email(message = "{Invalid_email_address}")
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Lob
    private byte[] photo;

    public User() {
        this.fullName = FullName.empty();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.username);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return getFullName().getSurname();
    }

    public void setSurname(String surname) {
        getFullName().setSurname(surname);
    }

    public String getName() {
        return getFullName().getName();
    }

    public void setName(String name) {
        getFullName().setName(name);
    }

    public String getPatronymic() {
        return getFullName().getPatronymic();
    }

    public void setPatronymic(String patronymic) {
        getFullName().setPatronymic(patronymic);
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
