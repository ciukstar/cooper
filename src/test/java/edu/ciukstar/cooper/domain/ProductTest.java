package edu.ciukstar.cooper.domain;

import static org.hamcrest.Matchers.is;
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
                .manufacturer(manufacturer).get();
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
                .manufacturer(manufacturer).get();
        
        assertThat(newProduct.getCode(), is(code));
        assertThat(newProduct.getName(), is(name));
        assertThat(newProduct.getDescription(), is(description));
        assertThat(newProduct.getManufacturer(), is(manufacturer));
        
    }
    private final Manufacturer manufacturer = mock(Manufacturer.class);
    private final String description = "A Description";
    private final String name = "The Name";
    private final String code = "The Code";
    
}
