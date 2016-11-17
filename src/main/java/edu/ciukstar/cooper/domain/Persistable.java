package edu.ciukstar.cooper.domain;

import java.io.Serializable;

/**
 *
 * @author sergiu
 */
public interface Persistable<T> extends Serializable {
    boolean isNew();
    T getId();
}
