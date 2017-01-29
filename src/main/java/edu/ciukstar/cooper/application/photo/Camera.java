package edu.ciukstar.cooper.application.photo;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.CaptureEvent;

/**
 *
 * @author sergiu
 */
@Named
@SessionScoped
public class Camera implements Serializable {
    
    private byte[] photo;
    
    public void capture(CaptureEvent e) {
        photo = e.getData();
    }

    public byte[] getPhoto() {
        return photo;
    }
    
}
