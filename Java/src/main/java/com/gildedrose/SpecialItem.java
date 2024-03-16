package com.gildedrose;

public enum SpecialItem {

    AGED_BRIE("Aged Brie"),
    SULFARAS("Sulfuras, Hand of Ragnaros"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    CONJURED("Conjured");
    private final String name;

    SpecialItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
