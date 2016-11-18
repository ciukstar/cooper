package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class UserRepo extends AbstractRepo<User> {
    @PersistenceContext(unitName = "cooper")
    private EntityManager em;
    @Inject
    private Event<List<User>> e;

    @Override
    public List<User> findAll() {
        final List<User> res = super.findAll();
        e.fire(res);
        return res;
    }


    public UserRepo() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
