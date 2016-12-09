package edu.ciukstar.cooper.domain;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class DispenserBuilder {

    public Dispenser forWarehouse(Warehouse warehouse) {
        final Dispenser res = new Dispenser();
        res.setWarehouse(warehouse);
        return res;
    }
    
    public Dispenser forPurchase(Purchase purchase) {
        final Dispenser res = new Dispenser();
        res.setPurchase(purchase);
        return res;
    }
    
}
