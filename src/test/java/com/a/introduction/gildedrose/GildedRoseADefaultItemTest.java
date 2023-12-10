package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {

	private static final String DEFAULT_ITEM_NAME = "DEFAULT_ITEM";
	private static final int QUALITY = 3;
	private static final int NON_EXPIRED_ITEM_SELL_IN = 15;
	private static final int EXPIRED_ITEM_SELL_IN = -1;

	@Test
	public void testNonExpiredItemQualityDecreasesByOne() {
		GildedRose app = setupGildedRoseWithItem(NON_EXPIRED_ITEM_SELL_IN, QUALITY);
		app.updateQuality();

		Item actualItem = getFirstItem(app);
		Item expectedItem = new Item(DEFAULT_ITEM_NAME, NON_EXPIRED_ITEM_SELL_IN - 1, QUALITY -1);

		assertItem(expectedItem, actualItem);
	}

	@Test
	public void testExpiredItemQualityDecreasesByTwo() {
		GildedRose app = setupGildedRoseWithItem(EXPIRED_ITEM_SELL_IN, QUALITY);
		app.updateQuality();

		Item actualItem = getFirstItem(app);
		Item expectedItem = new Item(DEFAULT_ITEM_NAME, EXPIRED_ITEM_SELL_IN - 1, QUALITY -2);

		assertItem(expectedItem, actualItem);
	}

	private GildedRose setupGildedRoseWithItem(int sellIn, int quality) {
		Item item = new Item(DEFAULT_ITEM_NAME, sellIn, quality);
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