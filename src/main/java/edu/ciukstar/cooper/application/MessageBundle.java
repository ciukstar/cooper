package edu.ciukstar.cooper.application;

import java.util.ResourceBundle;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class MessageBundle {
    
    public String getString(String key) {
        return ResourceBundle.getBundle("ValidationMessages", FacesContext.getCurrentInstance().getViewRoot().getLocale())
                .getString(key);
    }
}
