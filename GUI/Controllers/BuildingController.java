package BobbyHood.GUI.Controllers;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuildingController extends MasterController implements Initializable {
    @FXML
    ImageView bobby;

    @FXML
    private AnchorPane scene;

    private Stage stage;
    private Scene sceneswitch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeMovable(scene);
    }
    public void switchtoOutside(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Outside.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneswitch = new Scene(fxmlLoader.load());
        stage.setScene(sceneswitch);
        stage.show();
    }
}
