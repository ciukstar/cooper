package edu.ciukstar.cooper.domain;

import java.math.BigDecimal;
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
    
    private final Manufacturer vender = mock(Manufacturer.class);
    private final String photo = "rawdata";
    private final BigDecimal price = BigDecimal.ONE;
    private final String description = "A Description";
    private final String name = "The Name";
    private final String code = "The Code";
    
    @Test
    public void shouldBuildAnInvalidProductWhenAManadatoryPropertyIsNotSet() {
        Product product = ProductBuilder.from()
                .code(null)
                .name(name)
                .noDescription()
                .price(price)
                .noPhoto()
                .noVender()
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
                .price(price)
                .noPhoto()
                .noVender()
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
                .price(price)
                .photo(photo.getBytes())
                .vender(vender)
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
                .price(price)
                .photo(photo.getBytes())
                .vender(vender)
                .get();
        
        assertThat(product.getCode(), is(code));
        assertThat(product.getName(), is(name));
        assertThat(product.getDescription(), is(description));
        assertThat(product.getPrice(), is(price));
        assertThat(new String(product.getImage()), is(photo));
        assertThat(product.getManufacturer(), is(vender));
        
    }
    
}
