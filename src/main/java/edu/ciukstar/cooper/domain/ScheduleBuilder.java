package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class ScheduleBuilder {

    public Schedule emptySchedule() {
        return new Schedule();
    }
    
}
