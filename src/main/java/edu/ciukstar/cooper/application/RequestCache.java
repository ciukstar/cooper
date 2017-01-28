package edu.ciukstar.cooper.application;

import java.util.List;
import javax.el.ELContext;
import javax.el.LambdaExpression;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author sergiu
 */
@Named
@RequestScoped
public class RequestCache {

    private List<?> data;
    private LazyDataModel<?> lazyData;

    public LazyDataModel<?> getLazy(LambdaExpression ex) {
        if (lazyData == null) {
            ELContext ctx = FacesContext.getCurrentInstance().getELContext();
            lazyData = (LazyDataModel<?>) ex.invoke(ctx, new Object[]{});
        }
        return lazyData;
    }

    public List<?> get(LambdaExpression ex) {
        if (data == null) {
            ELContext ctx = FacesContext.getCurrentInstance().getELContext();
            data = (List<?>) ex.invoke(ctx, new Object[]{});
        }
        return data;
    }

}
