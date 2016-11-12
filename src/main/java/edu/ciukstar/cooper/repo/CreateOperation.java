package edu.ciukstar.cooper.repo;

/**
 *
 * @author sergiu
 */
public class CreateOperation<E> implements CrudOperation<E> {

    private final E entity;
    private final AbstractRepo<E> repo;

    public CreateOperation(E entity, AbstractRepo<E> repo) {
        this.entity = entity;
        this.repo = repo;
    }

    @Override
    public E execute() {
        repo.create(entity);
        return entity;
    }

    @Override
    public E getEntity() {
        return entity;
    }
}
