package BobbyHood.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private MovementController movementController =
            new MovementController();

    private static final String bobbyPath = "file:GUI/bobby.png";
    private final Image bobbyImage = new Image(bobbyPath);

    @FXML
    ImageView bobby = new ImageView(bobbyImage);

    @FXML
    private AnchorPane scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movementController.makeMovable(bobby, scene);
    }
}
