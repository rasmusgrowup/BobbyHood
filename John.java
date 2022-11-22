package BobbyHood;

public class John {
    String name;

    String[] dialog;

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
}
