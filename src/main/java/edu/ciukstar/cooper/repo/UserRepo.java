package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class UserRepo extends AbstractRepo<User> {

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;

    public UserRepo() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
