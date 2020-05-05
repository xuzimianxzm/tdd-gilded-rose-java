package cn.xpbootcamp.gilded_rose.test.domian;

import cn.xpbootcamp.gilded_rose.domain.entity.Commodity;
import cn.xpbootcamp.gilded_rose.exception.CommodityQualityInvalidException;
import org.junit.jupiter.api.Test;

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

}
