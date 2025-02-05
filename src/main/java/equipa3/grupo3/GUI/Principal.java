package equipa3.grupo3.GUI;

import equipa3.grupo3.GUI.Scenes.LoginApp;
import equipa3.grupo3.GUI.Scenes.ScenesController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Principal extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Parent() {};
		Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
		LoginApp loginApp = new LoginApp();
		loginApp.showLoginScreen(primaryStage);
	}

}
