package edu.ciukstar.cooper.application.purchase;

import edu.ciukstar.cooper.domain.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class OrganizerCache implements Serializable {

    private User organizer;
    
    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }
    
}
