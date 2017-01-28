package edu.ciukstar.cooper.domain;

import javax.ejb.ApplicationException;

/**
 *
 * @author sergiu
 */
@ApplicationException
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String string) {
        super(string);
    }

}
