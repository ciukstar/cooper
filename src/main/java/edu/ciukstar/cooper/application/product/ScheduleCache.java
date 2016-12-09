package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Schedule;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class ScheduleCache extends CrudCache<Schedule> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Schedule> op;
    private Schedule entity;

    void refresh(@Observes List<Schedule> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Schedule> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Schedule> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Schedule entity) {
        this.entity = entity;
    }

    @Override
    public Schedule getEntity() {
        return entity;
    }

}
