package edu.ciukstar.cooper.application.admin;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@RequestScoped
public class UserController {


    @Inject
    private UserCache cache;

}
