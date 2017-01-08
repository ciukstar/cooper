package edu.ciukstar.cooper.application;

import io.atlassian.fugue.Option;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author sergiu
 */
@Named
@RequestScoped
public class Messager {
    
    public void displayError(Option<String> message) {
        message.map(m -> new FacesMessage(FacesMessage.SEVERITY_ERROR, m, null))
                .forEach(fm -> {
                    FacesContext.getCurrentInstance().addMessage(null, fm);
                });
    }

}
