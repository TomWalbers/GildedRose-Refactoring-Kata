package com.gildedrose;

import java.util.Arrays;

public enum ItemName {

    AGED_BRIE("Aged Brie") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return quality + calculateNormalQualityChangeBySellIn(sellIn);
        }

        @Override
        public boolean isOfType(String value) {
            return this.getFullName().equals(value);
        }
    },
    SULFARAS("Sulfuras, Hand of Ragnaros") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return quality;
        }

        @Override
        public boolean isOfType(String value) {
            return this.getFullName().equals(value);
        }
    },
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return calculateBackstageQuality(quality, sellIn);
        }

        @Override
        public boolean isOfType(String value) {
            return this.getFullName().equals(value);
        }
    },
    CONJURED("Conjured") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return quality - calculateNormalQualityChangeBySellIn(sellIn) * 2;
        }

        @Override
        public boolean isOfType(String value) {
            return value.contains(this.getFullName());
        }
    },
    UNKNOWN("unknown") {
        @Override
        public int calculateNewQuality(int quality, int sellIn) {
            return quality - calculateNormalQualityChangeBySellIn(sellIn);
        }

        @Override
        public boolean isOfType(String value) {
            return false;
        }
    };

    private static int calculateNormalQualityChangeBySellIn(int sellIn) {
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
            .filter(s -> s.isOfType(value))
            .findFirst()
            .orElse(UNKNOWN);
    }

    public abstract int calculateNewQuality(int quality, int sellIn);

    public abstract boolean isOfType(String value);
}
