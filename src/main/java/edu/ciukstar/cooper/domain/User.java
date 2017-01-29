package edu.ciukstar.cooper.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.validation.constraints.NotNull;
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
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "{Username_may_not_be_blank}")
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @NotBlank(message = "{Password_may_not_be_blank}")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Embedded
    private FullName fullName;

    @ManyToOne
    @NotNull(message = "{Status_may_not_be_null}")
    @JoinColumn(name = "STATUS", referencedColumnName = "ID", nullable = false)
    private Status status;

    @Email(message = "{Invalid_email_address}")
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "CONFIDENCE")
    private BigDecimal confidence;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "ABOUT_ME")
    private String aboutMe;

    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                @JoinColumn(name = "USERID")},
            inverseJoinColumns = {
                @JoinColumn(name = "ROLEID")}, uniqueConstraints = {
                @UniqueConstraint(columnNames = {"USERID", "ROLEID"})})
    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>();
        this.fullName = FullName.empty();
    }

    public Graph getUserStatusGraph() {
        return userStatusGraph().orElse(null);
    }

    public Optional<Graph> userStatusGraph() {
        return getRoles().stream().map(Role::getUserStatusGraph).findAny();
    }

    public Graph getPurchaseStatusGraph() {
        return purchaseStatusGraph().orElse(null);
    }

    public Optional<Graph> purchaseStatusGraph() {
        return getRoles().stream().map(Role::getPurchaseStatusGraph).findAny();
    }

    public Graph getOrderStatusGraph() {
        return orderStatusGraph().orElse(null);
    }

    public Optional<Graph> orderStatusGraph() {
        return getRoles().stream().map(Role::getOrderStatusGraph).findAny();
    }

    public void initStatusIf(Boolean predicate) {
        if (predicate) {
            setStatus(userStatusGraph().map(Graph::getStartNode).orElse(null));
        }
    }

    public Set<Edge> getOutEdges() {
        return userStatusGraph().map(g -> g.getOutEdges(this)).orElse(Collections.EMPTY_SET);
    }

    public void transition(Edge edge) {
        userStatusGraph().ifPresent(g -> g.transition(this, edge));
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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
