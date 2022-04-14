package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;
    private final String code;

    public Product(final BigDecimal pricePerUnit, final String code) {
        this.pricePerUnit = pricePerUnit;
        this.code = code;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    String code() {
        return code;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
