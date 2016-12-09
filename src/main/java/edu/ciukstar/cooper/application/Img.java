package edu.ciukstar.cooper.application;

import edu.ciukstar.cooper.repo.AbstractRepo;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author sergiu
 */
@Named(value = "img")
@Dependent
public class Img implements Serializable {

    public <T> StreamedContent getContent(String entityId, String fieldName, AbstractRepo<T> repo) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {        
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            final T e = (T) repo.find(Long.valueOf(entityId));
            Field field = e.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            byte[] image = (byte[]) field.get(e);
            if (image != null) {
                return new DefaultStreamedContent(new ByteArrayInputStream(image));
            } else {
                return new DefaultStreamedContent();
            }
        }
    }

    public StreamedContent toStreamedContent(byte[] raw, String type) {
        if (raw != null && type != null) {
            return new DefaultStreamedContent(new ByteArrayInputStream(raw), type);
        } else {
            return new DefaultStreamedContent();
        }
    }

    public StreamedContent toStreamedContent(byte[] raw) {
        if (raw != null) {
            return new DefaultStreamedContent(new ByteArrayInputStream(raw));
        } else {
            return new DefaultStreamedContent();
        }
    }

}
