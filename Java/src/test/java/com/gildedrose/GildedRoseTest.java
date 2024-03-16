package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gildedrose.SpecialItem.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void shouldKeepTheSameName() {
        Item[] items = new Item[] { new Item("foo", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].name).isEqualTo("foo");
    }
    @Test
    void shouldLowerQualityAndSellIn() {
        Item[] items = new Item[] { new Item("foo", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(0);
        assertThat( app.items[0].sellIn).isEqualTo(0);
    }
    @Test
    void shouldLowerQualityTwiceAsFastWhenZero() {
        Item[] items = new Item[] { new Item("foo", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(3);
        assertThat( app.items[0].sellIn).isEqualTo(-1);
    }
    @Test
    void shouldLowerQualityTwiceAsFastWhenNegative() {
        Item[] items = new Item[] { new Item("foo", -1, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(3);
        assertThat( app.items[0].sellIn).isEqualTo(-2);
    }
    @Test
    void shouldIncreaseQualityWhenAgedBrie() {
        Item[] items = new Item[] { new Item(AGED_BRIE.getName(), 1, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(6);
        assertThat( app.items[0].sellIn).isEqualTo(0);
    }
    @Test
    void shouldIncreaseQualityTwiceAsFastWhenZeroWhenAgedBrie() {
        Item[] items = new Item[] { new Item(AGED_BRIE.getName(), 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(7);
        assertThat( app.items[0].sellIn).isEqualTo(-1);
    }
    @Test
    void shouldIncreaseQualityTwiceAsFastWhenNegativeWhenAgedBrie() {
        Item[] items = new Item[] { new Item(AGED_BRIE.getName(), -1, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(7);
        assertThat( app.items[0].sellIn).isEqualTo(-2);
    }

    @Test
    void shouldNotDecreaseQualityWhenSulfuras(){
        Item[] items = new Item[] { new Item(SULFARAS.getName(), 1, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(5);
        assertThat( app.items[0].sellIn).isEqualTo(0);
    }

    @Test
    void shouldIncreaseQualitySellInOverTenWhenBackstagePass(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS.getName(), 11, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(6);
        assertThat( app.items[0].sellIn).isEqualTo(10);
    }
    @Test
    void shouldIncreaseQualitySellInOverFiveWhenBackstagePass(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS.getName(), 10, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(7);
        assertThat( app.items[0].sellIn).isEqualTo(9);
    }
    @Test
    void shouldIncreaseQualityWhenBackstagePass(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS.getName(), 5, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(8);
        assertThat( app.items[0].sellIn).isEqualTo(4);
    }
    @Test
    void shouldHaveNoQualityWhenNegativeSellInWhenBackstagePass(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS.getName(), 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(0);
        assertThat( app.items[0].sellIn).isEqualTo(-1);
    }
    @Test
    void shouldNeverExceedQualityFifty(){
        Item[] items = new Item[] { new Item(AGED_BRIE.getName(), 1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat( app.items[0].quality).isEqualTo(50);
        assertThat( app.items[0].sellIn).isEqualTo(0);
    }

}
