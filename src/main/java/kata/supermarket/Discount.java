package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public interface Discount {
    BigDecimal applyDiscount(final List<Item> items);
}
