package BobbyHood.GUI.Controllers;

import BobbyHood.GUI.BobbyGUI;
import BobbyHood.Highscore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndController extends GameController implements Initializable {

    private Stage stage;
    CharacterController characterController = new CharacterController();
    Highscore scores;

    @FXML
    Text highscoreText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Highscore scores = new Highscore("highscores.txt");
        scores.writeHighscore(BobbyGUI.getGame().returnInventory().getCoins());
        highscoreText.setText(highscore(scores));
        System.out.println("Game ended");
    }

    public String highscore(Highscore scores) {
        StringBuilder string = new StringBuilder("");
        for (int i = 0; i < scores.getHighscores().size(); i++) {
            string.append(i+1).append(": ").append(scores.getHighscores().get(i)).append("\n");
        }
        String s = string.toString();
        return s;
    }
}
