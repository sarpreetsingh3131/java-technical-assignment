package kata.supermarket;

import kata.supermarket.discounts.BuyOneGetOneFree;
import kata.supermarket.discounts.BuyOneKiloForHalfPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasketWithDiscountTest {
    
    private static final String PINT_OF_MILK_CODE = "1A23";
    private static final String PACK_OF_DIGESTIVE_CODE = "4B56";
    private static final String AMERICAN_SWEETS_CODE = "7C89";
    private static final String PICK_AND_MIX_CODE = "1D56";


    @DisplayName("basket provides its total value after discount when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items, Map<String, Discount> discounts) {
        final Basket basket = new Basket(new ArrayList<>(), new DiscountRule(discounts));
        items.forEach(basket::add);

        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                multipleItemsPricedPerUnit(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "3.09",
                Arrays.asList(aKiloOfAmericanSweets(), twoHundredGramsOfPickAndMix()),
                discount(AMERICAN_SWEETS_CODE, new BuyOneKiloForHalfPrice(_aKiloOfAmericanSweets().pricePerKilo()))
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "3.59",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPintOfMilk(), aPintOfMilk()),
                discount(PINT_OF_MILK_CODE, new BuyOneGetOneFree())
        );
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList(), new HashMap<>());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), PINT_OF_MILK_CODE).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55"), PACK_OF_DIGESTIVE_CODE).oneOf();
    }

    private static WeighedProduct _aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"), AMERICAN_SWEETS_CODE);
    }

    private static Item aKiloOfAmericanSweets() {
        return _aKiloOfAmericanSweets().weighing(new BigDecimal("1"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"), PICK_AND_MIX_CODE);
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    private static Map<String, Discount> discount(String productCode, Discount discount) {
        Map<String, Discount> discountMap = new HashMap<>();
        discountMap.put(productCode, discount);
        return discountMap;
    }
}
