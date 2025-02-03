package equipa3.grupo3.GUI.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class ScenesController {

    public static void changeScene(Stage stage, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(ScenesController.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static UUID utilizadorID;



    public static void setUtilizadorID(UUID id) {

        utilizadorID = id;

    }

    public static UUID getUtilizadorID() {

        return utilizadorID;

    }
}