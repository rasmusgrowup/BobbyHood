package BobbyHood;

class Coin implements Item {
    private int amount;

    Coin (int amount) {
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

    public void setAmount(int amount) {
        this.amount = amount;
    }
}