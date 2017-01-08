package edu.ciukstar.cooper.repo;

import javax.ejb.ApplicationException;

/**
 *
 * @author sergiu
 */
@ApplicationException
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }
    
}
