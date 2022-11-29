package BobbyHood.GUI.Controllers;

import BobbyHood.Coin;
import BobbyHood.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    protected Game game;

    @FXML
    Pane pane = new Pane();
    @FXML
    private Text inventoryText = new Text();

    public void persistGame(Game game) {
        this.game = game;
        pane = new Pane();
        inventoryText.setText(game.getInventory());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inventoryText.setText(game.getInventory());
    }

    public void addCoins(ActionEvent event) throws IOException {
        game.returnInventory().addItem(new Coin(100));
        inventoryText.setText(game.getInventory());
    }
}