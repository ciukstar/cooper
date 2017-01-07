package edu.ciukstar.cooper.application;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;

/**
 *
 * @author sergiu
 */
public class CooperExceptionHanler extends ExceptionHandlerWrapper {

    private final ExceptionHandler handler;

    public CooperExceptionHanler(ExceptionHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle() throws FacesException {
        getUnhandledExceptionQueuedEvents().forEach(e -> {
            final Throwable ex = e.getContext().getException();
            FacesContext fctx = e.getContext().getContext();
            fctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        });
        super.handle();
    }
    
    @Override
    public ExceptionHandler getWrapped() {
        return handler;
    }
    
}
