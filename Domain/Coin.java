package BobbyHood.Domain;

public class Coin implements Item {
    private int amount;

    public Coin (int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String setType() {
        return null;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}