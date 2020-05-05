package cn.xpbootcamp.gilded_rose.test.domian;

import cn.xpbootcamp.gilded_rose.domain.entity.BackstagePass;
import cn.xpbootcamp.gilded_rose.domain.entity.Commodity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackstagePassTest {

    @Test
    void should_commodity_of_backstage_pass() {
        Commodity sulfuras = new BackstagePass().creatBackstagePass(12, 10);

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
}
