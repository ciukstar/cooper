package edu.ciukstar.cooper.application.admin;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author sergiu
 */
@Named
@ApplicationScoped
public class Janitor {
    
    private final List<UserSession> userSessions;

    public Janitor() {
        this.userSessions = new ArrayList<>();
    }
    
    public void registerUserSession(UserSession s) {
        this.userSessions.add(s);
    }
    
    public List<UserSession> getUserSessions() {
        return new ArrayList<>(this.userSessions);
    }

    public void unregisterUserSession(UserSession s) {
        this.userSessions.remove(s);
    }
    
}
