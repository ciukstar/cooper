package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class StatusRepo extends AbstractRepo<Status> {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Status> findAll() {
        return super.findAll();
    }
    
    public StatusRepo() {
        super(Status.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
