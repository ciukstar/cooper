package edu.ciukstar.cooper.domain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sergiu
 */
public class FullNameTest {

    @Before
    public void setUp() {

    }

    private final String surname = "Starciuc";
    private final String name = "Sergiu";
    private final String patronymic = "Victor";

    private FullName fullName;

    @Test
    public void shouldReturnFullNameStringRepresentationForWhenPatronymicIsUndefined() {

        fullName = FullName.from(surname, name, null);

        assertThat(fullName.toString(), is(String.format("%s %s",
                fullName.getSurname(), fullName.getName())));
    }

    @Test
    public void shouldReturnFullNameStringRepresentationForWhenAllPropertiesNotNull() {

        fullName = FullName.from(surname, name, patronymic);

        assertThat(fullName.toString(), is(String.format("%s %s %s",
                fullName.getSurname(), fullName.getName(), fullName.getPatronymic())));
    }

    @Test
    public void shouldCreateANewFullNameUsingOneStepBuilderPattern() {
        fullName = FullName.from().surname(surname).name(name).patronymic(patronymic).get();

        assertThat(fullName.getSurname(), is(surname));
        assertThat(fullName.getName(), is(name));
        assertThat(fullName.getPatronymic(), is(patronymic));
    }

    @Test
    public void shouldCreateANewFullNameUsingFactoryMethod() {
        fullName = FullName.from(surname, name, patronymic);

        assertThat(fullName.getSurname(), is(surname));
        assertThat(fullName.getName(), is(name));
        assertThat(fullName.getPatronymic(), is(patronymic));
    }

}
