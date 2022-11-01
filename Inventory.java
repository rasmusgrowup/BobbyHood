package BobbyHood;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

    Inventory (ArrayList<Item> items) {
        this.items = items;
    }
    Inventory () {
        this(new ArrayList<Item>());
    }

    public void addItem (Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public int getCoins() {
        int coins = 0;
        for (Item item: items) {
            if (item instanceof Coin) {
                coins += ((Coin) item).getAmount();
            }
        } return coins;
    }

    public void printInventory(Inventory inventory) {
        System.out.println();
        System.out.println("## Inventory status ##");
        System.out.println("Coins collected: " + inventory.getCoins());
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem(new Coin("coin", 100));
        inventory.addItem(new Coin("coin", 150));
        inventory.addItem(new Coin("coin", 50));
        inventory.printInventory(inventory);
    }
}
