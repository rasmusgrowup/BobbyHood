package BobbyHood.GUI.Controllers;

import BobbyHood.Domain.Highscore;
import BobbyHood.GUI.BobbyGUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class EndController extends GameController implements Initializable {
    @FXML
    Text highscoreText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Highscore scores = new Highscore("Data/highscores.txt");
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
