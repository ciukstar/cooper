package edu.ciukstar.cooper.domain;

import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sergiu
 */
public class FullNameBuilderTest {

    @Before
    public void setUp() {
    }
    private final String surname = "Starciuc";
    private final String name = "Sergiu";
    private final String patronymic = "Victor";

    private FullName fullName;

    @Test
    public void shouldCreateANewFullNameUsingOneStepBuilderPattern() {
        fullName = FullNameBuilder.from().surname(surname).name(name).patronymic(patronymic).get();

        assertThat(fullName.getSurname(), is(surname));
        assertThat(fullName.getName(), is(name));
        assertThat(fullName.getPatronymic(), is(patronymic));
    }

}
