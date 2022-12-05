package BobbyHood.GUI.Controllers;

import BobbyHood.GUI.BobbyGUI;
import BobbyHood.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController extends GameController implements Initializable {

    private Stage stage;
    private Scene sceneswitch;
    CharacterController characterController = new CharacterController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void switchToOutside(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Building.fxml"));
        stage = BobbyGUI.getStage();
        sceneswitch = new Scene(fxmlLoader.load());
        sceneswitch.getRoot().requestFocus();
        stage.setScene(sceneswitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
    }
}
