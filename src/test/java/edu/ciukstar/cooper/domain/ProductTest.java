package edu.ciukstar.cooper.domain;

import java.math.BigDecimal;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author sergiu
 */
public class ProductTest {
    
    @Before
    public void setUp() {
        product = Product.from().code(code).name(name).description(description)
                .price(price).noPhoto().vender(vender).get();
    }

    private Product product;
    
    
    @Test
    public void shouldReturnStringRepresentationOfAProduct() {
        assertThat(product.toString(),
                is(String.format("%s: %s", code, name)));
    }
    
    @Test
    public void shouldBuildAProductByUsingProductBuilderApi() {
        Product newProduct = Product.from().code(code).name(name).description(description)
                .price(price).noPhoto().vender(vender).get();
        
        assertThat(newProduct.getCode(), is(code));
        assertThat(newProduct.getName(), is(name));
        assertThat(newProduct.getDescription(), is(description));
        assertThat(newProduct.getPrice(), is(price));
        assertThat(newProduct.getImage(), is(nullValue()));
        assertThat(newProduct.getManufacturer(), is(vender));
        
    }
    private final Manufacturer vender = mock(Manufacturer.class);
    private final BigDecimal price = BigDecimal.ONE;
    private final String description = "A Description";
    private final String name = "The Name";
    private final String code = "The Code";
    
}
