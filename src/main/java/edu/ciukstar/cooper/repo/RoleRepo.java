package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Role;
import edu.ciukstar.cooper.domain.RoleType;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sergiu
 */
@Named
@Stateless
public class RoleRepo extends AbstractRepo<Role> {

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;
    @Inject
    private Event<List<Role>> e;

    public RoleRepo() {
        super(Role.class);
    }

    public RoleType[] findAllRoleTypes() {
        return RoleType.values();
    }
    
    @Override
    public List<Role> findAll() {
        final List<Role> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
