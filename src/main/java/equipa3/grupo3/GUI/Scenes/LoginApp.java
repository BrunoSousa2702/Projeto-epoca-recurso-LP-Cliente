package equipa3.grupo3.GUI.Scenes;

import equipa3.grupo3.services.ApiService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import equipa3.grupo3.GUI.ScenesController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class LoginApp implements Initializable {

    @FXML
    private Button button_login;

    @FXML
    private Button button_registar;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_password;

    private ApiService apiService = new ApiService();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        button_login.setOnAction(ae -> {
            Stage stage = (Stage) button_login.getScene().getWindow();
            System.out.println("Botão \"Entrar\" apertado.");

            try {
                // Login
                JSONObject json = new JSONObject();
                json.put("email", tf_email.getText());
                json.put("password", tf_password.getText());

                String response = apiService.postData("/auth", json.toString());
                JSONObject jsonResponse = new JSONObject(response);
                UUID idCliente = UUID.fromString(jsonResponse.getString("id"));
                ScenesController.setUtilizadorID(idCliente);

                // Carregar nova cena
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/equipa3/grupo3/GUI/Fxmls/allaround_menucliente.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                // Alerta de falha no login
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("As credenciais estão erradas, tente novamente.");
                alert.show();
                e.printStackTrace();
            }
        });

        button_registar.setOnAction(ae -> {
            System.out.println("Botão Registar Apertado");
            ScenesController.changeScene("/equipa3/grupo3/GUI/Fxmls/allaround_menuRegisto.fxml");
        });
    }
}
