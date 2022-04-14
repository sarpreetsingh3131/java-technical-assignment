package kata.supermarket;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, String productCode, String expectedPrice) {
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(pricePerKilo), productCode);
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "1A23", "100.00"),
                Arguments.of("100.00", "0.33333", "4B56", "33.33"),
                Arguments.of("100.00", "0.33335", "7C89", "33.34"),
                Arguments.of("100.00", "0", "1D35", "0.00")
        );
    }

}