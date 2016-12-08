package edu.ciukstar.cooper.domain;

import java.math.BigDecimal;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class ProductBuilder {

    public Product emptyProduct() {
        return new Product();
    }
    
    public static CodeStep from() {
        return new Steps();
    }
    
    public interface CodeStep {
        NameStep code(String value);
    }

    public interface NameStep {
        DescriptionStep name(String value);
    }

    public interface DescriptionStep {        
        ManufacturerStep description(String value);
        ManufacturerStep noDescription();
    }

    public interface ManufacturerStep {
        BuildStep manufacturer(Manufacturer manufacturer);
        BuildStep noManufacturer();
    }

    public interface BuildStep {
        Product get();
    }

    private static class Steps implements CodeStep, NameStep, DescriptionStep, ManufacturerStep, BuildStep {

        private String code;
        private String name;
        private String description;
        private Manufacturer vender;

        @Override
        public NameStep code(String value) {
            this.code = value;
            return this;
        }

        @Override
        public DescriptionStep name(String value) {
            this.name = value;
            return this;
        }

        @Override
        public ManufacturerStep description(String value) {
            this.description = value;
            return this;
        }

        @Override
        public ManufacturerStep noDescription() {
            this.description = null;
            return this;
        }

        @Override
        public BuildStep manufacturer(Manufacturer vender) {
            this.vender = vender;
            return this;
        }

        @Override
        public BuildStep noManufacturer() {
            this.vender = null;
            return this;
        }

        @Override
        public Product get() {
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setDescription(description);
            product.setManufacturer(vender);
            return product;
        }

    }
}
