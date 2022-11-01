package BobbyHood;

class Coin extends Item {
    private int amount;

    Coin (String type, int amount) {
        super(type);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}