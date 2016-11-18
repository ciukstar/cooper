package edu.ciukstar.cooper.domain;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class UserBuilder {
    
    public User newUser(Graph graph) {
        return newUser(graph.getStartNode());
    }
    
    public User newUser(Status status) {
        User user = new User();
        user.setStatus(status);
        return user;
    }
}
