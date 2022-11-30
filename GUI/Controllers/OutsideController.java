package BobbyHood.GUI.Controllers;


import BobbyHood.Coin;
import BobbyHood.GUI.BobbyGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OutsideController extends GameController implements Initializable {

    private final CharacterController characterController = new CharacterController();
    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby;
    @FXML
    Text inventoryText;

    @FXML
    Pane pane = new Pane();

    @FXML
    Rectangle door = new Rectangle();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String direction = "inside";
        characterController.makeMovable(bobby, scene, door, direction);
        door.setFill(Color.WHITE);
    }

    public void switchtoBuilding(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Building.fxml"));
        Stage stage = BobbyGUI.getStage();
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
    }
}
