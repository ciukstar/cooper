package edu.ciukstar.cooper.application.admin;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author sergiu
 */
@Named
@RequestScoped
public class Credentials {

    private String username;
    private String password;

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
    
}
