package edu.ciukstar.cooper.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class Dialog {

    private Integer width = 1000;
    private Integer height = 600;
    
    public void display(final String outcome) {
        RequestContext.getCurrentInstance().openDialog(outcome, options, Collections.EMPTY_MAP);
        
    }
    
    public void displayConfirmationDialog(final String outcome) {
        display(outcome);
    }

    public void dispose() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void displayInputForm(final String outcome) {
        display(outcome);
    }

    private final Map<String, Object> options;

    public Dialog() {
        this.options = new HashMap<String, Object>() {
            {
                put("modal", true);
                put("width", width);
                put("height", height);
                put("contentWidth", "100%");
                put("contentHeight", "100%");
            }
        };
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Dialog height(Integer height) {
        this.height = height;
        return this;
    }
    
    public Dialog width(Integer width) {
        this.width = width;
        return this;
    }
}
