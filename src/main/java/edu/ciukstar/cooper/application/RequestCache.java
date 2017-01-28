package edu.ciukstar.cooper.application;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

    private final Map<String, Collection<?>> cahe;
    private LazyDataModel<?> lazyData;

    public RequestCache() {
        this.cahe = new HashMap<>();
    }

    public LazyDataModel<?> getLazy(LambdaExpression ex) {

        if (lazyData == null) {
            ELContext ctx = FacesContext.getCurrentInstance().getELContext();
            lazyData = (LazyDataModel<?>) ex.invoke(ctx, new Object[]{});
        }
        return lazyData;
    }

    public Collection<?> get(String key, LambdaExpression ex) {
        return cahe.computeIfAbsent(key, k -> {
            ELContext ctx = FacesContext.getCurrentInstance().getELContext();
            return (Collection<?>) ex.invoke(ctx, new Object[]{});
        });
    }

}
