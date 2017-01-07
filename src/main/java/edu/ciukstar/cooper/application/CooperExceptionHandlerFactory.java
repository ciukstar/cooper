package edu.ciukstar.cooper.application;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author sergiu
 */
public class CooperExceptionHandlerFactory extends ExceptionHandlerFactory {

    private final ExceptionHandlerFactory factory;
    
    public CooperExceptionHandlerFactory(ExceptionHandlerFactory factory) {
        this.factory = factory;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new CooperExceptionHanler(factory.getExceptionHandler());
    }
    
}
