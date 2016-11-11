package edu.ciukstar.cooper.domain;

import java.math.BigDecimal;

/**
 *
 * @author sergiu
 */
public class ProductBuilder {

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
        PriceStep description(String value);
        PriceStep noDescription();
    }

    public interface PriceStep {
        PhotoStep price(BigDecimal value);
    }

    public interface PhotoStep {
        VenderStep photo(byte[] raw);
        VenderStep noPhoto();
    }

    public interface VenderStep {
        BuildStep vender(Vender vender);
        BuildStep noVender();
    }

    public interface BuildStep {
        Product get();
    }

    private static class Steps implements CodeStep, NameStep, DescriptionStep, PriceStep, PhotoStep, VenderStep, BuildStep {

        private String code;
        private String name;
        private String description;
        private BigDecimal price;
        private byte[] photo;
        private Vender vender;

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
        public PriceStep description(String value) {
            this.description = value;
            return this;
        }

        @Override
        public PriceStep noDescription() {
            this.description = null;
            return this;
        }

        @Override
        public PhotoStep price(BigDecimal value) {
            this.price = value;
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
        public BuildStep vender(Vender vender) {
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
            product.setPrice(price);
            product.setPhoto(photo);
            product.setVender(vender);
            return product;
        }

    }
}
