package fi.oulu.tol.sqat;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private static List<Item> items = null;

    public static void main(String[] args) {
        
        System.out.println("OMGHAI!");
        
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20)); // Normal item
        items.add(new Item("Aged Brie", 2, 0));            // Aged Brie
        items.add(new Item("Elixir of the Mongoose", 5, 7)); // Normal item
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80)); // Legendary item
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)); // Backstage passes
        items.add(new Item("Conjured Mana Cake", 3, 6));    // Conjured item

        updateQuality();
    }

    public static void updateQuality() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            
            // Handling normal items (not Aged Brie, Backstage Passes, or Sulfuras)
            if (!"Aged Brie".equals(item.getName()) && !"Backstage passes to a TAFKAL80ETC concert".equals(item.getName()) && !"Conjured Mana Cake".equals(item.getName())) {
                if (item.getQuality() > 0 && !"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                    item.setQuality(item.getQuality() - 1);
                }
            } else {
                // Aged Brie and Backstage passes logic
                if ("Aged Brie".equals(item.getName())) {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                } else if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                    if (item.getSellIn() <= 10 && item.getQuality() < 50) {
                            item.setQuality(item.getQuality() + 1); // +2 when sellIn <= 10                      
                    }
                    if (item.getSellIn() <= 5 && item.getQuality() < 50) {
                            item.setQuality(item.getQuality() + 1); // +3 when sellIn <= 5
                    }
                }
            }

            // Handle Sulfuras - no change in Quality or SellIn
            if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                item.setSellIn(item.getSellIn() - 1);
            }

            // Handle when sellIn < 0
            if (item.getSellIn() < 0) {
                if (!"Aged Brie".equals(item.getName())) {
                    if (!"Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                        // Quality decreases for normal items
                        if (item.getQuality() > 0 && !"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                            item.setQuality(item.getQuality() - 1);
                        }
                    } else {
                        // Backstage passes drop to 0 when sellIn < 0
                        item.setQuality(0);
                    }
                } else {
                    // Aged Brie increases in quality even after sellIn < 0, but max quality is 50
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
            }

            // Handle Conjured items - they decrease in quality twice as fast
            if ("Conjured Mana Cake".equals(item.getName())) {
                if (item.getQuality() > 0) {
                    item.setQuality(item.getQuality() - 2);  // Decrease quality twice as fast
                }
            }
        }
    }

    // Constructor
    public GildedRose() {
        items = new ArrayList<Item>();
    }

    // Getter
    public List<Item> getItems() {
        return items;
    }

    // Setter
    public void setItem(Item item) {
        items.add(item);
    }

    // Update one day
    public void oneDay() {
        updateQuality();
    }
}