package edu.ciukstar.cooper.application.graph;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Status;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class StatusCache extends CrudCache<Status> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Status> op;
    private Status entity;

    public void refresh(@Observes List<Status> source) {
        entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Status> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Status> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Status entity) {
        this.entity = entity;
    }

    @Override
    public Status getEntity() {
        return entity;
    }

}
