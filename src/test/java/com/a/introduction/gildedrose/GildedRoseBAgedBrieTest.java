package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseBAgedBrieTest {

	private static final String AGED_BRIE_NAME = "Aged Brie";
	private static final int DEFAULT_QUALITY = 3;
	private static final int MAX_QUALITY = 50;
	private static final int NON_EXPIRED_SELL_IN = 4;
	private static final int EXPIRED_SELL_IN = -1;

	@Test
	public void testNonExpiredAgedBrieQualityIncreasedByOne() {
 		GildedRose app = setupGildedRoseWithAgedBrie(NON_EXPIRED_SELL_IN, DEFAULT_QUALITY);
		app.updateQuality();

		Item actualAgedBrie = getFirstItem(app);
		Item expectedAgedBrie = new Item(AGED_BRIE_NAME, NON_EXPIRED_SELL_IN - 1 , DEFAULT_QUALITY + 1);

		assertItem(expectedAgedBrie, actualAgedBrie);
	}

	@Test
	public void testExpiredAgedBrieQualityIncreasedByTwo() {
		GildedRose app = setupGildedRoseWithAgedBrie(EXPIRED_SELL_IN, DEFAULT_QUALITY);
		app.updateQuality();

		Item actualAgedBrie = getFirstItem(app);
		Item expectedAgedBrie = new Item(AGED_BRIE_NAME, EXPIRED_SELL_IN -1 , DEFAULT_QUALITY + 2);

		assertItem(expectedAgedBrie, actualAgedBrie);
	}

	@Test
	public void testAgedBrieQualityBeyondMaximum() {
		GildedRose app = setupGildedRoseWithAgedBrie(NON_EXPIRED_SELL_IN, MAX_QUALITY);
		app.updateQuality();

		Item actualAgedBrie = getFirstItem(app);
		Item expectedAgedBrie = new Item(AGED_BRIE_NAME, NON_EXPIRED_SELL_IN - 1, MAX_QUALITY);

		assertItem(expectedAgedBrie, actualAgedBrie);
	}

	private GildedRose setupGildedRoseWithAgedBrie(int sellIn, int quality) {
		Item item = new Item(AGED_BRIE_NAME, sellIn, quality);
		Item[] items = new Item[] { item };
		return new GildedRose(items);
	}

	private Item getFirstItem(GildedRose app) {
		return app.items[0];
	}

	private void assertItem(Item expectedItem, Item actualItem) {
		assertEquals(expectedItem.name, actualItem.name);
		assertEquals(expectedItem.sellIn, actualItem.sellIn);
		assertEquals(expectedItem.quality, actualItem.quality);
	}
}
