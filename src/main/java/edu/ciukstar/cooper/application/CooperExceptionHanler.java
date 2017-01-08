package edu.ciukstar.cooper.application;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

/**
 *
 * @author sergiu
 */
public class CooperExceptionHanler extends ExceptionHandlerWrapper {
    private static final Logger LOGGER = Logger.getLogger(CooperExceptionHanler.class.getName());
    private final ExceptionHandler handler;

    public CooperExceptionHanler(ExceptionHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> q = getUnhandledExceptionQueuedEvents().iterator(); q.hasNext(); ) {
            ExceptionQueuedEvent e = q.next();
            final Throwable ex = e.getContext().getException();
            final FacesContext fctx = e.getContext().getContext();
            fctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            fctx.renderResponse();
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            q.remove();
        }
    }

    @Override
    public ExceptionHandler getWrapped() {
        return handler;
    }

}
