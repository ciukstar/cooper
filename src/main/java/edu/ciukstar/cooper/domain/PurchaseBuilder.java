package edu.ciukstar.cooper.domain;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class PurchaseBuilder {

    public Purchase emptyPurchase(Status status) {
        return new Purchase(status);
    }

    public Purchase emptyPurchase() {
        return new Purchase();
    }
    
}
