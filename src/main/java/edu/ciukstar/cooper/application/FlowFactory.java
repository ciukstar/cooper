package edu.ciukstar.cooper.application;

import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

/**
 *
 * @author sergiu
 */
public class FlowFactory implements Serializable {
    
    @Produces
    @FlowDefinition
    public Flow defineFlow(@FlowBuilderParameter FlowBuilder fb) {
        final String definingDocumentId = "crudUserFlow";
        final String flowId = "createUserFlow";
        fb.id(definingDocumentId, flowId);
        fb.viewNode("users", "/users/users.xhtml").markAsStartNode();
        fb.viewNode("user", "/users/user.xhtml");
        fb.viewNode("exit-flow", "/index.html");
        fb.returnNode("exit-flow").fromOutcome("home");
        return fb.getFlow();
    }
}
