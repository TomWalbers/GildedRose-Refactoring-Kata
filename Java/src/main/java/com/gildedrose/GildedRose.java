package com.gildedrose;


import com.sun.tools.javac.util.List;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        List<Item> itemList = List.from(items);
        itemList.stream()
            .peek(GildedRose::decreaseSellIn)
            .peek(item -> item.quality = calculateQuality(item))
            .peek(item -> item.quality = Integer.min(50, item.quality))
            .forEach(item -> item.quality = Integer.max(0, item.quality));
    }

    private static void decreaseSellIn(Item item1) {
        item1.sellIn--;
    }

    private int calculateQuality(Item item) {
        return ItemName.findByFullName(item.name).calculateNewQuality(item.quality, item.sellIn);
    }

}
