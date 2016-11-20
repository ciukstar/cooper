package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

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
