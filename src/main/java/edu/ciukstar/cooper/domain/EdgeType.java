package edu.ciukstar.cooper.domain;

/**
 *
 * @author sergiu
 */
public enum EdgeType {
    FORWARD, BACKWARD;

    public boolean isForward() {
        return this == FORWARD;
    }

    public boolean isBackward() {
        return this == BACKWARD;
    }

}
