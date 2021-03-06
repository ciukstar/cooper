package edu.ciukstar.cooper.application.purchase.rule;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Rule;
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
public class RuleCache extends CrudCache<Rule> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Rule> op;
    private Rule entity;

    public void uploadRuleIcon(FileUploadEvent e) {
        entity.setIcon(e.getFile().getContents());
    }
    
    void refresh(@Observes List<Rule> source) {
        this.entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Rule> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Rule> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Rule entity) {
        this.entity = entity;
    }

    @Override
    public Rule getEntity() {
        return entity;
    }

}
