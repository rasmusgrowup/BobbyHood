package BobbyHood.Domain;

public class John extends Person {
    String name;
    String[] dialog;
    private boolean engaged;

    public John () {
        name = "John";
    }

    public void setDialog(String[] dialog) {
        this.dialog = dialog;
    }

    public String getDialog(int index) {
        return dialog[index];
    }

    public String getName() {
        return name;
    }

    @Override
    public void setEngaged(boolean b) {
        engaged = b; //set the value of engaged
    }
    @Override
    public boolean getEngaged() {
        return engaged;
    }
    @Override
    public String getRejected() {
        return dialog[1]; //add a standard message to all npc's that bobby has talked to
    }
}
