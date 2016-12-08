package edu.ciukstar.cooper.domain;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author sergiu
 */
public class ProductBuilderTest {
    
    @Before
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private Validator validator;
    
    private final Manufacturer manufacturer = mock(Manufacturer.class);
    private final String description = "A Description";
    private final String name = "The Name";
    private final String code = "The Code";
    
    @Test
    public void shouldBuildAnInvalidProductWhenAManadatoryPropertyIsNotSet() {
        Product product = ProductBuilder.from()
                .code(null)
                .name(name)
                .noDescription()
                .noManufacturer()
                .get();
        
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations.size(), is(1));
    }
    
    @Test
    public void shouldBuildAValidProductWhenAllOptionalPropertiesAreNotSet() {
        Product product = ProductBuilder.from()
                .code(code)
                .name(name)
                .noDescription()
                .noManufacturer()
                .get();
        
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations, is(empty()));
    }
    
    @Test
    public void shouldBuildAValidProductWhenAllPropertiesAreSet() {
        Product product = ProductBuilder.from()
                .code(code)
                .name(name)
                .description(description)                
                .manufacturer(manufacturer)
                .get();
        
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations, is(empty()));
    }
    
    @Test
    public void shouldSetCorrectlyAllProductProperties() {
        Product product = ProductBuilder.from()
                .code(code)
                .name(name)
                .description(description)
                .manufacturer(manufacturer)
                .get();
        
        assertThat(product.getCode(), is(code));
        assertThat(product.getName(), is(name));
        assertThat(product.getDescription(), is(description));
        assertThat(product.getManufacturer(), is(manufacturer));
        
    }
    
}
