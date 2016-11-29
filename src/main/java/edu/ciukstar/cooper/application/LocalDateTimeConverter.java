package edu.ciukstar.cooper.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import org.primefaces.component.calendar.Calendar;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class LocalDateTimeConverter implements Converter {

    @Inject
    private MessageBundle msgs;

    private static final String DEFAULT = "dd.MM.yyyy HH:mm:ss";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String pattern = DEFAULT;
        if (component instanceof Calendar) {
            pattern = ((Calendar) component).getPattern();
        }
        try {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException ex) {
            FacesMessage msg = new FacesMessage(String.format("%s: %s", msgs.getString("Invalid_date_time_format"), value));
            throw new ConverterException(msg);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            LocalDateTime time = (LocalDateTime) value;
            String pattern = DEFAULT;
            if (component instanceof Calendar) {
                pattern = ((Calendar) component).getPattern();
            }
            return DateTimeFormatter.ofPattern(pattern).format(time);
        } catch (ClassCastException ex) {
            FacesMessage msg = new FacesMessage(String.format("%s: %s", msgs.getString("Invalid_object_type"), value.getClass().getName()));
            throw new ConverterException(msg);
        }
    }
}
