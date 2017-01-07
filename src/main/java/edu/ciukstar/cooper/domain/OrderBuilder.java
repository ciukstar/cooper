package edu.ciukstar.cooper.domain;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class OrderBuilder {

    public Order emptyOrderWithStatus(Status status) {
        return new Order(status);
    }
    
    public Order emptyOrder() {
        return new Order();
    }
}
