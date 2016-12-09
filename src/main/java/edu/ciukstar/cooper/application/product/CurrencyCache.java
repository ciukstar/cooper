package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Currency;
import edu.ciukstar.cooper.repo.CrudOperation;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class CurrencyCache extends CrudCache<Currency> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Currency> op;
    private Currency entity;

    public void uploadCurrencySymbol(FileUploadEvent e) {
        this.entity.setIcon(e.getFile().getContents());
    }
    
    void refresh(@Observes List<Currency> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }
    
    @Override
    protected void setCrudOperation(CrudOperation<Currency> op) {
        this.op = op;        
    }

    @Override
    protected CrudOperation<Currency> getCrudOperation() {
        return this.op;
    }

    @Override
    public void setEntity(Currency entity) {
        this.entity = entity;
    }

    @Override
    public Currency getEntity() {
        return this.entity;
    }

    
}
