package edu.ciukstar.cooper.domain;

/**
 *
 * @author sergiu
 */
public interface Persistable<T> {
    boolean isNew();
    T getId();
}
