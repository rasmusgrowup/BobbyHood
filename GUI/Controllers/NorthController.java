package BobbyHood.GUI.Controllers;


import BobbyHood.Coin;
import BobbyHood.GUI.BobbyGUI;
import BobbyHood.Game;
import BobbyHood.Person;
import BobbyHood.Room;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class NorthController extends GameController implements Initializable {

    private final CharacterController characterController = new CharacterController();
    private HashMap<String, Rectangle> doors = new HashMap<>();
    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby, person;
    @FXML
    Text inventoryText;
    @FXML
    Rectangle doorBuilding, doorEast, doorSouth, doorWest = new Rectangle();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        characterController.makeMovable(bobby, scene, doors);
        doorBuilding.setFill(Color.TRANSPARENT);
        doorEast.setFill(Color.TRANSPARENT);
        doorSouth.setFill(Color.TRANSPARENT);
        doorWest.setFill(Color.TRANSPARENT);
        doors.put("building", doorBuilding);
        doors.put("east", doorEast);
        doors.put("south", doorSouth);
        doors.put("west", doorWest);
        //System.out.println(game.currentRoom.getShortDescription());
    }
}
