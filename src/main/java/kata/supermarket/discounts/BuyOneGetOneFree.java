package kata.supermarket.discounts;

import java.math.BigDecimal;
import java.util.List;

import kata.supermarket.Discount;
import kata.supermarket.Item;

public final class BuyOneGetOneFree implements Discount {
    
    public BigDecimal applyDiscount(final List<Item> items) {
        return items.stream()
        .findFirst()
        .map(item -> item.price().multiply(new BigDecimal(items.size() / 2)))
        .orElse(BigDecimal.ZERO);
    }
}
