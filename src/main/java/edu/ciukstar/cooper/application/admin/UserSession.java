package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.domain.Role;
import edu.ciukstar.cooper.domain.User;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class UserSession implements Serializable {

    private User user;
    private Role role;
    private LocalDateTime start;

    public UserSession setupUserSession(User user) {
        this.user = user;
        this.role = user.getRoles().stream().findAny().orElseThrow(() -> new RuntimeException());
        this.start = LocalDateTime.now();
        return this;
    }

    public Optional<String> menu() {
        return role().flatMap(Role::mainMenu);
    }
    
    public LocalDateTime getStart() {
        return start;
    }

    public User getUser() {
        return user;
    }

    public Optional<Role> role() {
        return Optional.ofNullable(role);
    }

    public Role getRole() {
        return role;
    }
    
}
