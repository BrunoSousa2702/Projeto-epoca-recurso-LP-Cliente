package equipa3.grupo3.GUI.Scenes;
import equipa3.grupo3.GUI.Model.Tarefa;
import equipa3.grupo3.GUI.Model.Tarefa.Estado;
import equipa3.grupo3.GUI.Model.Tarefa.Prioridade;
import equipa3.grupo3.GUI.Model.Utilizador;
import equipa3.grupo3.services.ApiService;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UtilizadorMenu {
    private static ApiService apiService = new ApiService();

    public static void showMainMenu(Utilizador utilizador) {
        Stage mainStage = new Stage();
        mainStage.setTitle("Menu Principal " + utilizador.getEmail());

        // Criando os botões
        Button btnVerTarefas = new Button("Ver Tarefas");
        Button btnVerPerfil = new Button("Ver Perfil");
        Button btnAdicionarTarefa = new Button("Adicionar Tarefa");
        Button btnSair = new Button("Sair");

        // Configurando a GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Adicionando os botões à GridPane
        grid.add(btnVerTarefas, 0, 0);
        grid.add(btnVerPerfil, 1, 0);
        grid.add(btnAdicionarTarefa, 0, 1);
        grid.add(btnSair, 1, 1);

        Scene menuScene = new Scene(grid, 400, 300);
        mainStage.setScene(menuScene);
        mainStage.show();

        // Eventos dos botões
        btnVerTarefas.setOnAction(e -> showTaskList(utilizador));
        btnVerPerfil.setOnAction(e -> showUserProfile(utilizador));
        btnAdicionarTarefa.setOnAction(e -> showAddTaskScreen(utilizador));
        btnSair.setOnAction(e -> Platform.exit());
    }

    // Método para abrir a tela de lista de tarefas
    private static void showTaskList(Utilizador utilizador) {
        Stage taskStage = new Stage();
        taskStage.setTitle("Suas Tarefas " +utilizador.getNome());

        ListView<Tarefa> listView = new ListView<>();
        Button btnEditar = new Button("Editar");
        Button btnEliminar = new Button("Eliminar");
        Button btnVoltar = new Button("Voltar");

        btnEditar.setDisable(true);
        btnEliminar.setDisable(true);

        // Layout
        VBox vbox = new VBox(10, listView, btnEditar, btnEliminar, btnVoltar);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 400);
        taskStage.setScene(scene);
        taskStage.show();

        // Carregar tarefas do utilizador
        carregarTarefas(utilizador.getId(), listView);

        // Habilitar botões ao selecionar uma tarefa
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isSelected = newSelection != null;
            btnEditar.setDisable(!isSelected);
            btnEliminar.setDisable(!isSelected);
        });

        // Eventos dos botões
        btnVoltar.setOnAction(e -> taskStage.close());
        btnEliminar.setOnAction(e -> eliminarTarefa(listView.getSelectionModel().getSelectedItem(), listView));
        btnEditar.setOnAction(e -> editarTarefa(listView.getSelectionModel().getSelectedItem(), utilizador));
    }

    // Método para carregar tarefas
    private static void carregarTarefas(int utilizadorId, ListView<Tarefa> listView) {
        try {
            System.out.println("Entrei na função carregarTarefas");
            String url = "/tarefas/utilizador/" + utilizadorId;
            String response = apiService.getData(url);
    
            // Verificar a resposta da API antes de processá-la
            System.out.println("Resposta da API: " + response);
    
            JSONArray jsonArray = new JSONArray(response);
            List<Tarefa> tarefas = new ArrayList<>();
    
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
    
                int userId = 0;
                Utilizador utilizador = null;
    
                // Verifica se "utilizador_id" existe no JSON antes de acessá-lo
                if (json.has("utilizador_id")) {
                    userId = json.getInt("utilizador_id");
                    utilizador = apiService.getUserById(userId);
                } else {
                    System.out.println("⚠️ Campo 'utilizador_id' não encontrado para a tarefa: " + json.toString());
                    continue; // Pula esta tarefa se não houver um utilizador associado
                }
    
                // Conversão de data segura
                LocalDate data = json.has("data") ? LocalDate.parse(json.getString("data"), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : LocalDate.now();
    
                // Tratamento seguro de valores
                Prioridade prioridade = json.has("prioridade") ? Prioridade.valueOf(json.getString("prioridade")) : Prioridade.MEDIA;
                Estado estado = json.has("estado") ? Estado.valueOf(json.getString("estado")) : Estado.ATIVO;
    
                Tarefa tarefa = new Tarefa(
                json.getInt("id"),  // Certifique-se de que o JSON contém o campo "id"
                json.getString("titulo"),
                json.getString("descricao"),
                data,
                prioridade,
                estado,
                utilizador
                );

    
                tarefas.add(tarefa);
            }
    
            listView.getItems().setAll(tarefas);
        } catch (Exception e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
            e.printStackTrace(); // Exibe o erro completo para debugging
        }
    }
    

    //deleteData
    
    // Método para eliminar uma tarefa
    private static void eliminarTarefa(Tarefa tarefa, ListView<Tarefa> listView) {
        if (tarefa == null) return;

        try {
            String url = "http://localhost:8080/api/tarefas/" + tarefa.getId();
            //apiService.deleteData(url);

            // Remover da listView
            listView.getItems().remove(tarefa);
            System.out.println("Tarefa eliminada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao eliminar tarefa: " + e.getMessage());
        }
    }

    // Método para editar uma tarefa (abre um novo stage para edição)
    private static void editarTarefa(Tarefa tarefa, Utilizador utilizador) {
        if (tarefa == null) return;

        Stage editStage = new Stage();
        editStage.setTitle("Editar Tarefa");

        TextField tfTitulo = new TextField(tarefa.getTitulo());
        TextField tfDescricao = new TextField(tarefa.getDescricao());
        Button btnSalvar = new Button("Salvar");
        Button btnCancelar = new Button("Cancelar");

        VBox vbox = new VBox(10, new Label("Título:"), tfTitulo, new Label("Descrição:"), tfDescricao, btnSalvar, btnCancelar);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        editStage.setScene(scene);
        editStage.show();

        btnCancelar.setOnAction(e -> editStage.close());
        btnSalvar.setOnAction(e -> {
            try {
                JSONObject json = new JSONObject();
                json.put("titulo", tfTitulo.getText());
                json.put("descricao", tfDescricao.getText());

                String url = "http://localhost:8080/api/tarefas/" + tarefa.getId();
                apiService.putData(url, json.toString());

                System.out.println("Tarefa editada com sucesso.");
                editStage.close();
                showTaskList(utilizador); // Atualiza a lista de tarefas
            } catch (Exception ex) {
                System.out.println("Erro ao editar tarefa: " + ex.getMessage());
            }
        });
    }

    // Método para abrir a tela de adicionar tarefa (em construção)
    private static void showAddTaskScreen(Utilizador utilizador) {
        System.out.println("Tela de Adicionar Tarefa - Em construção");
    }

    // Método para abrir a tela de perfil do usuário (em construção)
    private static void showUserProfile(Utilizador utilizador) {
        System.out.println("Tela de Ver Perfil - Em construção");
    }
}
