package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final String code;

    public WeighedProduct(final BigDecimal pricePerKilo, final String code) {
        this.pricePerKilo = pricePerKilo;
        this.code = code;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    String code() {
        return code;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
