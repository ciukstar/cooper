package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.domain.Schedule;
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
public class ScheduleRepo extends AbstractRepo<Schedule> {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<List<Schedule>> e;

    public ScheduleRepo() {
        super(Schedule.class);
    }

    @Override
    public List<Schedule> findAll() {
        final List<Schedule> res = super.findAll();
        e.fire(res);
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
