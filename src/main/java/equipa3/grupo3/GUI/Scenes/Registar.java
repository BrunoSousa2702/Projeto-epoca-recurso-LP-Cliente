package equipa3.grupo3.GUI.Scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import equipa3.grupo3.services.ApiService;
import org.json.JSONObject;
import java.net.URL;
import java.util.ResourceBundle;

public class Registar implements Initializable {

    @FXML
    private Button button_registarr;

    @FXML
    private Button button_voltar;

    @FXML
    private RadioButton rb_cliente;

    @FXML
    private RadioButton rb_profissional;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_nome;

    @FXML
    private PasswordField tf_password;

    private ApiService apiService = new ApiService();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_cliente.setToggleGroup(toggleGroup);
        rb_profissional.setToggleGroup(toggleGroup);
        rb_cliente.setSelected(true);

        button_voltar.setOnAction(ae -> {
            System.out.println("Botão Voltar Apertado");
            ScenesController.changeScene(((Stage) button_registarr.getScene().getWindow()), "/equipa3/grupo3/GUI/Fxmls/login.fxml");
        });

        button_registarr.setOnAction(ae -> {
            System.out.println("Botão Registar Apertado");
            try {
                JSONObject json = new JSONObject();
                json.put("nome", tf_nome.getText());
                json.put("email", tf_email.getText());
                json.put("password", tf_password.getText());
                json.put("username", tf_username.getText());
                json.put("userType", ((RadioButton) toggleGroup.getSelectedToggle()).getText().toUpperCase());
                json.put("codigo", 0);

                apiService.postData("/utilizadores", json.toString());
                ScenesController.changeScene(((Stage) button_registarr.getScene().getWindow()), "/equipa3/grupo3/GUI/Fxmls/login.fxml");
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Não foi possível realizar o registo, verifique se não há campo em branco.");
                alert.show();
                e.printStackTrace();
            }
        });
    }
}
