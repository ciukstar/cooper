package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class CategoryBuilder implements Serializable {
    public Category emptyCategory() {
        return new Category();
    }
}
