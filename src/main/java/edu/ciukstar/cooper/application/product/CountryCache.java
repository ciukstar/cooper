package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Country;
import edu.ciukstar.cooper.repo.CrudOperation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class CountryCache extends CrudCache<Country> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Country> op;
    private Country entity;

    public void uploadFlag(FileUploadEvent e) {
        entity.setFlag(e.getFile().getContents());
    }
    
    void refresh(@Observes List<Country> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Country> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Country> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Country entity) {
        this.entity = entity;
    }

    @Override
    public Country getEntity() {
        return entity;
    }

    
}
