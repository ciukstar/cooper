package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

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
