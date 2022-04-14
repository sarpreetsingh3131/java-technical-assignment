package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiscountRule {
    
    private final Map<String, Discount> discounts;

    public DiscountRule(final Map<String, Discount> discounts) {
        this.discounts = discounts;
    }

    public void addDiscount(String productCode, Discount discount) {
        discounts.put(productCode, discount);
    }

    public BigDecimal getDiscount(final List<Item> items) {
        if (discounts.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return items.stream()
            .collect(Collectors.groupingBy(Item::code))
            .entrySet()
            .stream()
            .map(entry -> Optional.ofNullable(discounts.get(entry.getKey()))
                            .map(discount -> discount.applyDiscount(entry.getValue()))
                            .orElse(BigDecimal.ZERO))
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    }
}
