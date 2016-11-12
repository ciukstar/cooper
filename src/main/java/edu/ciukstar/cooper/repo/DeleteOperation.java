package edu.ciukstar.cooper.repo;

public class DeleteOperation<E> implements CrudOperation<E> {

    private final E entity;
    private final AbstractRepo<E> repo;

    public DeleteOperation(E entity, AbstractRepo<E> repo) {
        this.entity = entity;
        this.repo = repo;

    }

    @Override
    public E perform() {
        repo.remove(entity);
        return entity;
    }

    @Override
    public E getEntity() {
        return entity;
    }

}
