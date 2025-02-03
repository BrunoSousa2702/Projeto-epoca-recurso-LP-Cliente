package equipa3.grupo3.GUI.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class ScenesController {

    private static Stage mainStage;  // Guardar a referência do Stage principal
    private static UUID utilizadorID;

    // Método para definir o Stage principal
    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void changeScene(String fxml) {
        if (mainStage == null) {
            System.err.println("Erro: O Stage principal (mainStage) não foi inicializado!");
            return;
        }

        try {
            // Certificar que o caminho do FXML não é nulo
            URL fxmlLocation = ScenesController.class.getResource(fxml);
            if (fxmlLocation == null) {
                System.err.println("Erro: O arquivo FXML '" + fxml + "' não foi encontrado!");
                return;
            }

            Parent root = FXMLLoader.load(fxmlLocation);
            Scene scene = new Scene(root);

            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar a cena: " + fxml);
            e.printStackTrace();
        }
    }

    public static void setUtilizadorID(UUID id) {
        utilizadorID = id;
    }

    public static UUID getUtilizadorID() {
        return utilizadorID;
    }
}