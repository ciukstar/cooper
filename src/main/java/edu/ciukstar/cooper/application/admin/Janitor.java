package edu.ciukstar.cooper.application.admin;

import edu.ciukstar.cooper.application.MessageBundle;
import edu.ciukstar.cooper.domain.User;
import edu.ciukstar.cooper.repo.AuthenticationException;
import io.atlassian.fugue.Either;
import io.atlassian.fugue.Option;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author sergiu
 */
@Named
@ApplicationScoped
public class Janitor {
    @Inject
    private MessageBundle msg;
    private final List<UserSession> userSessions;

    public Janitor() {
        this.userSessions = new ArrayList<>();
    }
    
    public User validateCredentials(Credentials c) {
        throw new UnsupportedOperationException();
    }
    
    public boolean isRegistered(UserSession session) {
        return getUserSessions().stream().anyMatch(s -> s.equals(session));
    }
    
    public Either<AuthenticationException, UserSession> validateCredentials(UserSession s, Either<AuthenticationException, User> user) {
        return user.map(u -> { s.setupUserSession(u); return s; });
    }
    
    public Option<Exception> registerUserSession(Either<AuthenticationException, UserSession> s) {
        return s.map(us -> { this.userSessions.add(us); return us; })
                .fold(ex -> Option.some(new AuthenticationException(msg.getString("Invalid_username_or_password"))), us -> Option.none(Exception.class));
    }
    
    public List<UserSession> getUserSessions() {
        return new ArrayList<>(this.userSessions);
    }

    public void destroyCurrentUserSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public void unregisterUserSession(UserSession s) {
        this.userSessions.remove(s);        
    }
    
}
