package cn.xpbootcamp.gilded_rose.domain.entity;

import java.util.function.BiFunction;

public class BackstagePass extends Commodity {

    public Commodity creatBackstagePass(int sellIn, int quality) {
        return new Commodity("Backstage pass", sellIn, quality, getBackstagePassCondition());
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
