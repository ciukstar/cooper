package edu.ciukstar.cooper.application;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class Dialog {

    public void dispose() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    public void displayInputForm(final String outcome) {
        RequestContext.getCurrentInstance().openDialog(outcome);
    }
    
}
