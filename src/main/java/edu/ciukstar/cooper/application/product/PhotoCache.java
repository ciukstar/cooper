package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Photo;
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
public class PhotoCache extends CrudCache<Photo> implements Serializable {

    @Inject
    private Refresher refresher;
    private CrudOperation<Photo> op;
    private Photo entity;

    public void uploadPhoto(FileUploadEvent e) {
        entity.setRaw(e.getFile().getContents());
    }
    
    void refresh(@Observes List<Photo> source) {
        this.entity = refresher.match(this.entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Photo> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Photo> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Photo entity) {
        this.entity = entity;
    }

    @Override
    public Photo getEntity() {
        return entity;
    }

}
