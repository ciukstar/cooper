package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class RuleBuilder {

    public Rule emptyRule() {
        return new Rule();
    }
    
}
