package edu.ciukstar.cooper.repo;

public class UpdateOperation<E> implements CrudOperation<E> {

    private final E entity;
    private final AbstractRepo<E> repo;

    public UpdateOperation(E entity, AbstractRepo<E> repo) {
        this.entity = entity;
        this.repo = repo;
    }

    @Override
    public E execute() {
        return repo.edit(entity);
    }

    @Override
    public E getEntity() {
        return entity;
    }

}
