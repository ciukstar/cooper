package edu.ciukstar.cooper.application;

import edu.ciukstar.cooper.repo.CrudOperation;

/**
 *
 * @author sergiu
 * @param <T> the entity
 */
public abstract class CrudCache<T> {

    protected abstract void setCrudOperation(CrudOperation<T> op);

    protected abstract CrudOperation<T> getCrudOperation();

    public abstract void setEntity(T entity);

    public abstract T getEntity();

    public void schedule(CrudOperation<T> crud) {
        setCrudOperation(crud);
        setEntity(crud.getEntity());
    }

    public void executeCrudOperation() {
        getCrudOperation().execute();
    }

    public void cancelCrudOperation() {
        setEntity(null);
        setCrudOperation(null);
    }
}
