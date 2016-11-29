package edu.ciukstar.cooper.application;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.component.calendar.Calendar;

/**
 *
 * @author sergiu
 */
@RunWith(MockitoJUnitRunner.class)
public class LocalDateTimeConverterTest {

    @Before
    public void setUp() {
    }
    private final FacesContext ctx = mock(FacesContext.class);

    @Mock
    private MessageBundle msgs;

    @InjectMocks
    private LocalDateTimeConverter converter;

    @Test
    public void shouldThrowConverterExceptionIfInputObjectIsNotOfExpectedType() {
        Calendar cal = mock(Calendar.class);        
        final Object input = new Object() {};
        
        when(cal.getPattern()).thenReturn("dd.MM.yyyy HH:mm:ss");
        when(msgs.getString("Invalid_object_type")).thenReturn("Invalid object type");

        try {
            converter.getAsString(ctx, cal, input);
        } catch (ConverterException ex) {
            assertThat(ex.getFacesMessage().getSummary(), is(String.format("%s: %s", 
                    msgs.getString("Invalid_object_type"), input.getClass().getName())));
        }
    }

    @Test
    public void shouldFormatInputUsingThePatternOfCalendar() {
        Calendar cal = mock(Calendar.class);
        when(cal.getPattern()).thenReturn("dd-MM-yyyy HH:mm:ss");
        final LocalDateTime input = LocalDateTime.of(2016, Month.NOVEMBER, 29, 22, 50, 32);

        String res = converter.getAsString(ctx, cal, input);

        assertThat(res, is(DateTimeFormatter.ofPattern(cal.getPattern()).format(input)));
    }

    @Test
    public void shouldFormatInputLocalDateTimeWithDefaultPattern() {

        String res = converter.getAsString(ctx, mock(UIComponent.class), LocalDateTime.of(2016, Month.NOVEMBER, 29, 22, 50, 32));

        assertThat(res, is("29.11.2016 22:50:32"));
    }

    @Test
    public void shouldThrowAConverterExceptionIfInvalidDateTimeFormat() {
        final Calendar cal = mock(Calendar.class);
        final String value = "29-11-2016 22:50:32";

        when(cal.getPattern()).thenReturn("dd.MM.yyyy HH:mm:ss");
        when(msgs.getString("Invalid_date_time_format")).thenReturn("Invalid date time format");

        try {
            converter.getAsObject(ctx, cal, value);
            fail();
        } catch (ConverterException ex) {
            assertThat(ex.getFacesMessage().getSummary(), is(
                    String.format("%s: %s", msgs.getString("Invalid_date_time_format"), value)
            ));
        }
    }

    @Test
    public void shouldUseCalendarPattern() {
        final Calendar cal = mock(Calendar.class);
        when(cal.getPattern()).thenReturn("dd-MM-yyyy HH:mm:ss");
        final String value = "29-11-2016 22:50:32";

        LocalDateTime res = (LocalDateTime) converter.getAsObject(ctx, cal, value);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(cal.getPattern());

        assertThat(res, is(LocalDateTime.parse(value, formatter)));
    }

    @Test
    public void shouldParseAsLocalDateTimeUsingDefaultPattern() {

        Object res = converter.getAsObject(ctx, mock(UIComponent.class), "29.11.2016 22:50:32");

        assertThat(res, is(instanceOf(LocalDateTime.class)));
    }

}
