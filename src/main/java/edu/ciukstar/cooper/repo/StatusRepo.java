package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class StatusRepo extends AbstractRepo<Status> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Status>> e;
    
    @Override
    public List<Status> findAll() {
        final List<Status> res = super.findAll();
        e.fire(res);
        return res;
    }
    
    public StatusRepo() {
        super(Status.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
