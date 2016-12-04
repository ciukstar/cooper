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
        PhotoStep description(String value);
        PhotoStep noDescription();
    }

    public interface PhotoStep {
        VenderStep photo(byte[] raw);
        VenderStep noPhoto();
    }

    public interface VenderStep {
        BuildStep vender(Manufacturer vender);
        BuildStep noVender();
    }

    public interface BuildStep {
        Product get();
    }

    private static class Steps implements CodeStep, NameStep, DescriptionStep, PhotoStep, VenderStep, BuildStep {

        private String code;
        private String name;
        private String description;
        private BigDecimal price;
        private byte[] photo;
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
        public PhotoStep description(String value) {
            this.description = value;
            return this;
        }

        @Override
        public PhotoStep noDescription() {
            this.description = null;
            return this;
        }

        @Override
        public VenderStep photo(byte[] raw) {
            this.photo = raw;
            return this;
        }

        @Override
        public VenderStep noPhoto() {
            this.photo = null;
            return this;
        }

        @Override
        public BuildStep vender(Manufacturer vender) {
            this.vender = vender;
            return this;
        }

        @Override
        public BuildStep noVender() {
            this.vender = null;
            return this;
        }

        @Override
        public Product get() {
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setDescription(description);
            product.setImage(photo);
            product.setManufacturer(vender);
            return product;
        }

    }
}
