package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseCBackstagePassesTest {

	public static final String BACKSTAGE_PASSES_NAME = "Backstage passes to a TAFKAL80ETC concert";
	public static final int BEYOND_TEN_DAYS_SELL_IN = 15;
	public static final int DEFAULT_QUALITY = 3;
	public static final int BETWEEN_FIVE_AND_TEN_SELL_IN = 7;
	public static final int LESS_THAN_FIVE_SELL_IN = 4;

	@Test
	public void testBackStagePassesBeyondTenDaysIncreasedQualityByOne() {
		GildedRose app = setupGildedRoseWithAgedBrie(BEYOND_TEN_DAYS_SELL_IN, DEFAULT_QUALITY);
		app.updateQuality();

		Item actualAgedBrie = getFirstItem(app);
		Item expectedAgedBrie = new Item(BACKSTAGE_PASSES_NAME, BEYOND_TEN_DAYS_SELL_IN - 1 , DEFAULT_QUALITY + 1);

		assertItem(expectedAgedBrie, actualAgedBrie);
	}

	@Test
	public void testBackStagePassesBetweenFiveAndTenDaysIncreasedQualityByTwo() {
		GildedRose app = setupGildedRoseWithAgedBrie(BETWEEN_FIVE_AND_TEN_SELL_IN, DEFAULT_QUALITY);
		app.updateQuality();

		Item actualAgedBrie = getFirstItem(app);
		Item expectedAgedBrie = new Item(BACKSTAGE_PASSES_NAME, BETWEEN_FIVE_AND_TEN_SELL_IN - 1 , DEFAULT_QUALITY + 2);

		assertItem(expectedAgedBrie, actualAgedBrie);
	}

	@Test
	public void testUpdateQualityBackstagePasses3() {
		GildedRose app = setupGildedRoseWithAgedBrie(LESS_THAN_FIVE_SELL_IN, DEFAULT_QUALITY);
		app.updateQuality();

		Item actualAgedBrie = getFirstItem(app);
		Item expectedAgedBrie = new Item(BACKSTAGE_PASSES_NAME, LESS_THAN_FIVE_SELL_IN - 1, DEFAULT_QUALITY + 3);

		assertItem(expectedAgedBrie, actualAgedBrie);
	}

	private GildedRose setupGildedRoseWithAgedBrie(int sellIn, int quality) {
		Item item = new Item(BACKSTAGE_PASSES_NAME, sellIn, quality);
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