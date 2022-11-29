package BobbyHood.GUI;
import BobbyHood.GUI.Controllers.GameController;
import BobbyHood.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BobbyGUI extends Application {
    public Game game = new Game();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Building.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(game);
        scene.getRoot().requestFocus();
        stage.setTitle("BobbyHood");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setHeight(800);
        stage.setWidth(1200);
        stage.show();
    }
}
