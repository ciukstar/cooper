package edu.ciukstar.cooper.domain;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class CountryBuilder {

    public Country emptyCountry() {
        return new Country();
    }
    
}
