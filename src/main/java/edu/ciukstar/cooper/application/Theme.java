package edu.ciukstar.cooper.application;

/**
 *
 * @author sergiu
 */
public class Theme {
    private final int id;
    private final String name;
    private final String displayName;

    public Theme(int id, String displayName, String name) {
        this.displayName = displayName;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}
