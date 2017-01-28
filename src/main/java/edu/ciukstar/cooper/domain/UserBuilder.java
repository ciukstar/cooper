package edu.ciukstar.cooper.domain;

import edu.ciukstar.cooper.application.MessageBundle;
import edu.ciukstar.cooper.repo.RoleRepo;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class UserBuilder {

    @Inject
    private RoleRepo roleRepo;
    @Inject
    private MessageBundle msg;

    public User newEmptyUser() {
        return new User();
    }

    public User newParticipant() {
        final RoleType roleType = RoleType.PARTICIPANT;
        Role role = roleRepo.findByType(roleType).orElseThrow(
                () -> new RoleNotFoundException(
                        String.format("%s: %s", msg.getString("Role_not_found"), roleType)
                ));
        User participant = new User();
        participant.addRole(role);
        return participant;
    }

    public User newUser(Graph graph) {
        return newUser(graph.getStartNode());
    }

    public User newUser(Status status) {
        User user = new User();
        user.setStatus(status);
        return user;
    }
}
