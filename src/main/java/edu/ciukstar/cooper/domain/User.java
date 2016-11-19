package edu.ciukstar.cooper.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
public class User implements Persistable<Long>, StatusTrackable {

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

    @ManyToOne
    @JoinColumn(name = "status_graph", referencedColumnName = "id", nullable = false)
    private Graph statusGraph;

    @Email(message = "{Invalid_email_address}")
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Lob
    private byte[] photo;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = {
                @JoinColumn(name = "userid")},
            inverseJoinColumns = {
                @JoinColumn(name = "roleid")}, uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userid", "roleid"})})
    private List<Role> roles;

    public User() {
        this.fullName = FullName.empty();
    }

    public void initStatusIf(Boolean predicate) {
        if (predicate) {
            setStatus(statusGraph.getStartNode());
        }
    }

    public Set<Edge> getOutEdges() {
        return statusGraph.getOutEdges(this);
    }

    public void transition(Edge edge) {
        statusGraph.transition(this, edge);
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

    @Override
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

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    public Graph getStatusGraph() {
        return statusGraph;
    }

    public void setStatusGraph(Graph statusGraph) {
        this.statusGraph = statusGraph;
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

    public List<Role> getRoles() {
        return new ArrayList<>(roles);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }
    
    @Override
    public boolean isNew() {
        return null == getId();
    }

}
