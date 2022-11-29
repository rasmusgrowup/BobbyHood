package BobbyHood.GUI.Controllers;

import BobbyHood.GUI.BobbyGUI;
import BobbyHood.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuildingController extends GameController implements Initializable {

    private CharacterController characterController = new CharacterController();

    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby;
    @FXML
    Text inventoryText;

    @FXML
    Pane pane = new Pane();

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        characterController.makeMovable(bobby, scene);
    }

    public void switchtoOutside(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Outside.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(game);
    }
}
