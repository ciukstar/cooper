package edu.ciukstar.cooper.domain;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class ResourceBuilder {

    public Resource newResourceFor(Role role) {
        return new Resource(role);
    }
    
}
