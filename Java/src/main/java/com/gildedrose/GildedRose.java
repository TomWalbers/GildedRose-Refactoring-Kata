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
            .forEach(item -> item.quality = calculateQuality(item));
    }

    private static void decreaseSellIn(Item item1) {
        item1.sellIn--;
    }

    private int calculateQuality(Item item) {
        int newQuality = ItemType.findByItemName(item.name).calculateNewQuality(item.quality, item.sellIn);
        newQuality = Integer.min(50, newQuality);
        newQuality = Integer.max(0, newQuality);
        return newQuality;
    }

}
