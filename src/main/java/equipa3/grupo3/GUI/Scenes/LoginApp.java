package equipa3.grupo3.GUI.Scenes;

import equipa3.grupo3.services.ApiService;
import equipa3.grupo3.GUI.Model.Utilizador;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.util.UUID;

public class LoginApp extends Application {
    private ApiService apiService = new ApiService();
    private TextField tf_email;
    private PasswordField tf_password;
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginScreen(primaryStage);
    }
    
    public void showLoginScreen(Stage primaryStage) {
        tf_email = new TextField();
        tf_email.setPromptText("Email");

        tf_password = new PasswordField();
        tf_password.setPromptText("Senha");

        Button button_login = new Button("Entrar");
        Button button_registar = new Button("Registar");

        Label label = new Label("Bem-vindo! Faça login abaixo.");
        
        VBox root = new VBox(10, label, tf_email, tf_password, button_login, button_registar);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(300, 200);
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        button_login.setOnAction(ae -> login());
        button_registar.setOnAction(ae -> showRegisterScreen(primaryStage));
    }
    
    private void login() {
        String username = tf_email.getText();
        String password = tf_password.getText();
    
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }
    
        try {
            String url = "/utilizadores/login?email=" + username + "&password=" + password;
            System.out.println(url);
            String response = apiService.getData(url);
            System.out.println(url);
            JSONObject jsonResponse = new JSONObject(response);
            Integer idCliente = jsonResponse.getInt("id");
            System.out.println("ID do cliente obtido: " + idCliente);
            //get user by id
            Utilizador userJson = apiService.getUserById(idCliente);
            System.out.println("Utilizador obtido: " + userJson);
            ScenesController.setUtilizadorID(idCliente);
            UtilizadorMenu.showMainMenu(userJson);
        } catch (Exception e) {
            System.out.println("Nome de usuário ou senha incorretos." + e);

        }
    }
    
    
    private void showRegisterScreen(Stage primaryStage) {
        primaryStage.setTitle("Registro de Utilizador");
    
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    
        // Labels e campos de texto
        Label lblEmail = new Label("Email:");
        TextField tfEmail = new TextField();
    
        Label lblNome = new Label("Nome:");
        TextField tfNome = new TextField();
    
        Label lblUsername = new Label("Username:");
        TextField tfUsername = new TextField();
    
        Label lblPassword = new Label("Password:");
        PasswordField pfPassword = new PasswordField();
    
        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;"); // Mensagem de erro em vermelho
    
        Button btnRegister = new Button("Registrar");
    
        // Adiciona os componentes ao GridPane
        gridPane.add(lblEmail, 0, 0);
        gridPane.add(tfEmail, 1, 0);
    
        gridPane.add(lblNome, 0, 1);
        gridPane.add(tfNome, 1, 1);
    
        gridPane.add(lblUsername, 0, 2);
        gridPane.add(tfUsername, 1, 2);
    
        gridPane.add(lblPassword, 0, 3);
        gridPane.add(pfPassword, 1, 3);
    
        gridPane.add(btnRegister, 1, 4);
        gridPane.add(lblError, 1, 5);
    
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
    
        // Ação do botão registrar
        btnRegister.setOnAction(e -> {
            String email = tfEmail.getText();
            String nome = tfNome.getText();
            String username = tfUsername.getText();
            String password = pfPassword.getText();
    
            if (email.isEmpty() || nome.isEmpty() || username.isEmpty() || password.isEmpty()) {
                lblError.setText("Os campos não podem estar vazios.");
                return;
            }
    
            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("nome", nome);
            json.put("username", username);
            json.put("password", password);
            json.put("is_admin", false); // Sempre falso
    
            try {
                ApiService apiService = new ApiService();
                String response = apiService.postData("/utilizadores", json.toString());
                System.out.println("Registro bem-sucedido: " + response); // Mensagem de sucesso no console
                lblError.setText("Registro realizado com sucesso!");
                lblError.setStyle("-fx-text-fill: green;"); // Muda a cor para verde em caso de sucesso
                showLoginScreen(primaryStage);
            } catch (Exception ex) {
                System.out.println("Erro ao registrar: " + ex.getMessage()); // Exibe erro no console
                lblError.setText("Erro ao registrar. Tente novamente.");
            }
        });
    }
    


    
    public static void main(String[] args) {
        launch(args);
    }
}
