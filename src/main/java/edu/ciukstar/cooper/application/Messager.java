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

    public Option<String> unwrapMessage(Exception ex) {
        return Option.option(ex.getMessage());
    }
    
    public void displayErrorMessage(String message) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

}
