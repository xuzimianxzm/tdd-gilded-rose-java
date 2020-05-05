package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.exception.CommodityQualityInvalidException;

import java.util.function.BiFunction;

public class Commodity {
    private String name;
    private int sellIn;
    private int quality;
    private BiFunction<Commodity, Integer, Integer> condition;

    public Commodity() {
    }

    public Commodity(String name, int sellIn, int quality, BiFunction<Commodity, Integer, Integer> condition) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.condition = condition;
        checkQualityInvalid();
    }

    public int getQualityByDays(int days) {
        quality = condition.apply(this, days);
        sellIn = sellIn - days;
        checkQualityInvalidAndReset();
        return quality;
    }

    private void checkQualityInvalidAndReset() {
        if (quality > 50) {
            quality = 50;
        }
        if (quality < 0) {
            quality = 0;
        }
    }

    private void checkQualityInvalid() {
        if (quality > 50 || quality < 0) {
            throw new CommodityQualityInvalidException();
        }
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }
}