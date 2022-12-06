package BobbyHood.GUI.Controllers;

import BobbyHood.GUI.BobbyGUI;
import BobbyHood.Domain.Door;
import BobbyHood.Domain.John;
import BobbyHood.Domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

public class BuildingController extends GameController implements Initializable {

    private final CharacterController characterController = new CharacterController();
    private HashMap<String, Door> doors = new HashMap<>();
    private HashMap<Person, ImageView> persons = new HashMap();
    private ArrayList<ImageView> images = new ArrayList<>();
    private Door door;
    private John johnCharacter;

    private Stage stage;
    private Scene sceneswitch;

    @FXML
    private AnchorPane scene;
    @FXML
    Pane dialogPane;
    @FXML
    ImageView bobby, john;
    @FXML
    Text inventoryText, dialogText;
    @FXML
    Rectangle doorRect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        images.add(john);
        setPersonsForRoom(persons, images);
        this.door = new Door();
        this.johnCharacter = new John();
        door.setRect(doorRect);
        door.setFxmlPath("fxml/North.fxml");
        door.setDirection("north");
        doors.put("north", door);
        characterController.setPersons(persons);
        characterController.makeMovable(bobby, scene, doors);
        doorRect.setFill(Color.TRANSPARENT);
        characterController.setBorderValues(120, 1200, 800);
        dialogText.setText("Press 'enter' to talk with John");
        dialogText.setStyle("-fx-font: 18 monospace;");
        characterController.setPaused();
    }

    public void endGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/End.fxml"));
        stage = BobbyGUI.getStage();
        sceneswitch = new Scene(fxmlLoader.load());
        sceneswitch.getRoot().requestFocus();
        stage.setScene(sceneswitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
    }
}
