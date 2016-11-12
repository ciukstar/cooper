package edu.ciukstar.cooper.repo;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class Crud {

    public CrudOperation create(Object entity, AbstractRepo<?> repo) {
        return new CreateOperation(entity, repo);
    }

    public CrudOperation update(Object entity, AbstractRepo<?> repo) {
        return new UpdateOperation(entity, repo);
    }

    public CrudOperation delete(Object entity, AbstractRepo<?> repo) {
        return new DeleteOperation(entity, repo);
    }
    
}
