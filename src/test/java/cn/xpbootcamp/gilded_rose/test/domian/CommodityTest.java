package cn.xpbootcamp.gilded_rose.test.domian;

import cn.xpbootcamp.gilded_rose.domain.entity.Commodity;
import cn.xpbootcamp.gilded_rose.exception.CommodityQualityInvalidException;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommodityTest {

    @Test
    void should_commodity_when_quality_invalid() {
        assertThrows(CommodityQualityInvalidException.class, () -> new Commodity("any", 1, 51, null));
        assertThrows(CommodityQualityInvalidException.class, () -> new Commodity("any", 1, -1, null));
    }

    @Test
    void should_commodity_of_sulfuras() {
        Commodity sulfuras = new Commodity("sulfuras", 0, 10, (commodity, days) -> commodity.getQuality());

        assertEquals(10, sulfuras.getQualityByDays(1));
        assertEquals(10, sulfuras.getQualityByDays(10));
    }

    @Test
    void should_commodity_of_aged_brie() {
        Commodity sulfuras = new Commodity("Aged Brie", 7, 10, (commodity, days) -> commodity.getQuality() + days);

        assertEquals(11, sulfuras.getQualityByDays(1));
        assertEquals(6, sulfuras.getSellIn());
        assertEquals(21, sulfuras.getQualityByDays(10));
        assertEquals(-4, sulfuras.getSellIn());
    }

    @Test
    void should_commodity_of_backstage_pass() {
        Commodity sulfuras = new Commodity("Backstage pass", 12, 10, getBackstagePassCondition());

        assertEquals(10, sulfuras.getQualityByDays(0));
        assertEquals(12, sulfuras.getSellIn());

        assertEquals(11, sulfuras.getQualityByDays(1));
        assertEquals(11, sulfuras.getSellIn());

        assertEquals(13, sulfuras.getQualityByDays(1));
        assertEquals(10, sulfuras.getSellIn());

        assertEquals(21, sulfuras.getQualityByDays(5));
        assertEquals(5, sulfuras.getSellIn());

        assertEquals(24, sulfuras.getQualityByDays(1));
        assertEquals(4, sulfuras.getSellIn());

        assertEquals(0, sulfuras.getQualityByDays(4));
        assertEquals(0, sulfuras.getSellIn());
    }

    private BiFunction<Commodity, Integer, Integer> getBackstagePassCondition() {
        return (commodity, days) -> {
            int increase = 0, sellIn = commodity.getSellIn();

            if (0 == days) {
                return commodity.getQuality();
            }

            for (int day = 1; day <= days; day++) {
                sellIn = sellIn - 1;

                if (sellIn > 10) {
                    increase += 1;
                } else if (sellIn > 5) {
                    increase += 2;
                } else if (sellIn < 5 && sellIn > 0) {
                    increase += 3;
                } else if (sellIn <= 0) {
                    return 0;
                }
            }
            return increase + commodity.getQuality();
        };
    }
}
