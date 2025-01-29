package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}

	@Test
	public void exampleTest() {
		// create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();

		// access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}

	@Test
	public void testAgedBrieIncreasesQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 20));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Aged Brie quality increases by 1
		assertEquals("Failed quality for Aged Brie", 21, quality);
	}

	@Test
	public void testBackstagePassesIncreaseQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Backstage passes quality increases by 1
		assertEquals("Failed quality for Backstage passes", 22, quality);
	}

	@Test
	public void testBackstagePassesIncreaseMoreWhenSellInLessThan10() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 9, 20));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Backstage passes quality increases by 2
		assertEquals("Failed quality for Backstage passes when SellIn < 10", 22, quality);
	}

	@Test
	public void testBackstagePassesIncreaseMostWhenSellInLessThan5() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 20));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Backstage passes quality increases by 3
		assertEquals("Failed quality for Backstage passes when SellIn < 5", 23, quality);
	}

	@Test
	public void testBackstagePassesQualityDropsToZeroAfterConcert() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Backstage passes quality becomes 0 after the concert
		assertEquals("Failed quality for Backstage passes after SellIn < 0", 0, quality);
	}

	@Test
	public void testSulfurasNeverChanges() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		int sellIn = items.get(0).getSellIn();

		// Assert Sulfuras quality and sellIn do not change
		assertEquals("Failed quality for Sulfuras", 80, quality);
		assertEquals("Failed SellIn for Sulfuras", 0, sellIn);
	}

	@Test
	public void testDexterityVestDecreasesQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Dexterity Vest quality decreases by 1
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}

	@Test
	public void testDexterityVestQualityDoesNotGoNegative() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 0));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Dexterity Vest quality doesn't go below 0
		assertEquals("Failed to prevent negative quality for Dexterity Vest", 0, quality);
	}

	@Test
	public void testItemQualityDecreasesTwiceAsFastAfterSellIn() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 0, 10));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert quality decreases twice as fast after sellIn < 0
		assertEquals("Failed to decrease quality twice as fast after SellIn < 0", 8, quality);
	}

	@Test
	public void testAgedBrieDoesNotExceed50Quality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 50));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Aged Brie quality does not exceed 50
		assertEquals("Failed to prevent Aged Brie quality from exceeding 50", 50, quality);
	}

	@Test
	public void testConjuredItemQualityDecreasesTwiceAsFast() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 10, 6));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		// Assert Conjured item quality decreases twice as fast
		assertEquals("Failed to decrease quality twice as fast for Conjured item", 4, quality);
	}

	// Additional Test Cases for Full Coverage of Loop

	@Test
	public void testRegularItemQualityDecreases() {
		// Regular item - quality should decrease by 1 each day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		assertEquals("Failed to decrease quality for regular item", 19, quality);
	}

	@Test
	public void testRegularItemQualityDecreasesTwiceAsFastAfterSellIn() {
		// Regular item - quality should decrease twice as fast after sellIn < 0
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 0, 10));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		assertEquals("Failed to decrease quality twice as fast after sellIn < 0", 8, quality);
	}

	@Test
	public void testSulfurasQualityAndSellInNeverChange() {
		// Sulfuras should not change in quality or sellIn
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		int sellIn = items.get(0).getSellIn();

		assertEquals("Failed to keep quality of Sulfuras unchanged", 80, quality);
		assertEquals("Failed to keep sellIn of Sulfuras unchanged", 0, sellIn);
	}

	@Test
	public void testItemQualityDecreasesTwiceAsFastAfterSellInT() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 0, 10)); // sellIn = 0
		inn.oneDay();

		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();

		assertEquals("Quality should decrease by 2 after SellIn = 0", 8, quality);
	}
}
