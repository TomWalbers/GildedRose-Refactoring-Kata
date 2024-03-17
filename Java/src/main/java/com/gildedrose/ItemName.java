package com.gildedrose;

import java.util.Arrays;

public enum ItemName {

    AGED_BRIE("Aged Brie") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return quality + calculateQualityChangeBySellIn(sellIn);
        }
    },
    SULFARAS("Sulfuras, Hand of Ragnaros") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return quality;
        }
    },
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return calculateBackstageQuality(quality, sellIn);
        }
    },
    UNKNOWN("unknown") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return quality - calculateQualityChangeBySellIn(sellIn);
        }
    };

    private static int calculateQualityChangeBySellIn(int sellIn) {
        return sellIn < 0 ? 2 : 1;
    }

    private static int calculateBackstageQuality(int quality, int sellIn) {
        if (sellIn < 0) {
            return 0;
        }
        if (sellIn < 5) {
            return quality + 3;
        }
        if (sellIn < 10) {
            return quality + 2;
        }
        return quality + 1;
    }

    private final String fullName;

    ItemName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public static ItemName findByFullName(String value) {
        return Arrays.stream(values())
            .filter(s -> s.getFullName().equals(value))
            .findFirst()
            .orElse(UNKNOWN);
    }

    public abstract int calculateNewQuality(int quality, int sellIn);
}
