package edu.ciukstar.cooper.domain;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class PhotoBuilder {

    public Photo emptyPhoto() {
        return new Photo();
    }
    
}
