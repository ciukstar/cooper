package edu.ciukstar.cooper.repo;

/**
 *
 * @author sergiu
 */
public interface CrudOperation<E> {

    public E getEntity();
    
    public E perform();
    
}
