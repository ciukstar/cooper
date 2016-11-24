package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class OrderBuilder {

    public Order emptyOrder() {
        return new Order();
    }
}
