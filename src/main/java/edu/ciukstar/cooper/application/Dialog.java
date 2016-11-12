package edu.ciukstar.cooper.application;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    private final Map<String, Object> options;

    public Dialog() {
        this.options = new HashMap<String, Object>() {
            {
                put("modal", true);
                put("width", 700);
                put("height", 400);
                put("contentWidth", "100%");
                put("contentHeight", "100%");
            }
        };
    }

    public void displayReadonlyForm(final String outcome) {
        Map<String, List<String>> params = new HashMap<>();
        params.put("readonly", Arrays.asList("true"));
        RequestContext.getCurrentInstance().openDialog(outcome, options, params);
    }

    public void dispose() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void displayInputForm(final String outcome) {
        RequestContext.getCurrentInstance().openDialog(outcome, options, Collections.EMPTY_MAP);
    }

}
