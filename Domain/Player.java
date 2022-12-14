package BobbyHood.Domain;

import java.util.HashMap;

public class Player {
    private HashMap<Person, String[]> dialog;

    public Player() {
        String name = "Bobby";
        this.dialog = new HashMap<>();
    }

    public void setDialog(Person person, String[] dialog) {
        this.dialog.put(person, dialog);
    }

    public String getDialog(Person person, int i) {
        String[] d = dialog.get(person);
        return "Bobby: " + d[i];
    }
}
