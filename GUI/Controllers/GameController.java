package BobbyHood.GUI.Controllers;

import BobbyHood.Domain.Coin;
import BobbyHood.GUI.BobbyGUI;
import BobbyHood.Domain.Game;
import BobbyHood.Domain.Person;
import BobbyHood.Domain.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    protected Game game;
    protected ArrayList<ImageView> images;
    @FXML
    Pane pane = new Pane();
    @FXML
    private Text inventoryText = new Text();

    public void persistGame(Game game) {
        this.game = game;
        pane = new Pane();
        inventoryText.setText(BobbyGUI.getGame().getInventory());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventoryText.setText(game.getInventory());
    }

    public void addCoins(ActionEvent event) throws IOException {
        game.returnInventory().addItem(new Coin(100));
        inventoryText.setText(game.getInventory());
    }

    public void setPersonsForRoom(HashMap<Person, ImageView> persons, ArrayList<ImageView> images) {
        int index = 0;
        Room room = BobbyGUI.getGame().getCurrentRoom();
        HashMap<String, Person> list = room.getPersonsList();
        for (HashMap.Entry<String, Person> set : list.entrySet()) {
            persons.put(set.getValue(), images.get(index));
            System.out.println(set.getKey());
            index++;
        }
    }
}
