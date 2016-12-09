package edu.ciukstar.cooper.application.product;

import edu.ciukstar.cooper.application.CrudCache;
import edu.ciukstar.cooper.application.Refresher;
import edu.ciukstar.cooper.domain.Manufacturer;
import edu.ciukstar.cooper.repo.CrudOperation;
import edu.ciukstar.cooper.repo.ManufacturerRepo;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class ManufacturerCache extends CrudCache<Manufacturer> implements Serializable {

    @Inject
    private ManufacturerRepo repo;
    @Inject
    private Refresher refresher;
    private CrudOperation<Manufacturer> op;
    private Manufacturer entity;

    public StreamedContent logoAsStreamedContent(String paramName) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            String id = context.getExternalContext().getRequestParameterMap().get(paramName);

            byte[] image = repo.find(Long.valueOf(id)).getLogo();

            return new DefaultStreamedContent(new ByteArrayInputStream(image));

        }
    }

    public void uploadLogo(FileUploadEvent e) {
        entity.setLogo(e.getFile().getContents());
    }

    void refresh(@Observes List<Manufacturer> source) {
        entity = refresher.match(entity, source).orElse(null);
    }

    @Override
    protected void setCrudOperation(CrudOperation<Manufacturer> op) {
        this.op = op;
    }

    @Override
    protected CrudOperation<Manufacturer> getCrudOperation() {
        return op;
    }

    @Override
    public void setEntity(Manufacturer entity) {
        this.entity = entity;
    }

    @Override
    public Manufacturer getEntity() {
        return entity;
    }

}
