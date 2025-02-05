package equipa3.grupo3.GUI.Scenes;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class ScenesController {

    private static Stage mainStage;  // Guardar a referência do Stage principal
    private static Integer utilizadorID;

    // Método para definir o Stage principal
    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    

    public static void setUtilizadorID(Integer id) {
        utilizadorID = id;
    }

    public static Integer getUtilizadorID() {
        return utilizadorID;
    }
}