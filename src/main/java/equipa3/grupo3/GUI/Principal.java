package equipa3.grupo3.GUI;

import equipa3.grupo3.GUI.Scenes.ScenesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Principal extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("Fxmls\\menuLogin.fxml"));	

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
		ScenesController.setMainStage(primaryStage);
		ScenesController.changeScene("/equipa3/grupo3/GUI/Fxmls/menulogin.fxml");
	}

}
