package BobbyHood.GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BobbyGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("Building.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getRoot().requestFocus();
            stage.setTitle("BobbyHood");
            stage.setScene(scene);
            stage.show();
    }
}
