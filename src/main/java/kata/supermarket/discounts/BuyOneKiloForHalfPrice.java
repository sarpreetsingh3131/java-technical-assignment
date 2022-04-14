package kata.supermarket.discounts;

import java.math.BigDecimal;
import java.util.List;

import kata.supermarket.Discount;
import kata.supermarket.Item;

public final class BuyOneKiloForHalfPrice implements Discount {

    private final BigDecimal aKiloPrice;

    public BuyOneKiloForHalfPrice(final BigDecimal aKiloPrice) {
        this.aKiloPrice = aKiloPrice;
    }

    public BigDecimal applyDiscount(final List<Item> items) {
        return items.stream()
        .map(Item::price)
        .reduce(BigDecimal::add)
        .filter(totalPrice -> totalPrice.compareTo(aKiloPrice) == 0)
        .map(totalPrice -> totalPrice.divide(new BigDecimal(2)))
        .orElse(BigDecimal.ZERO);
    }    
}
