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

    public String printInventory(Inventory inventory) {
        return "## Inventory status ##" + "\n" + "Coins collected: " + inventory.getCoins();
        //System.out.println();
        //System.out.println("## Inventory status ##");
        //System.out.println("Coins collected: " + inventory.getCoins());
    }
}
