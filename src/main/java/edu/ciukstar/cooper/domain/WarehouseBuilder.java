package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class WarehouseBuilder {

    public Warehouse emptyWarehouse() {
        return new Warehouse();
    }
    
}
