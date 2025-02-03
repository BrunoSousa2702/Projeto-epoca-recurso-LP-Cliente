package equipa3.grupo3.GUI.Scenes;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tabela_tarefas {

    @FXML
    private TableView<Tarefa> tabelaTarefas;

    @FXML
    private TableColumn<Tarefa, Integer> colunaId;

    @FXML
    private TableColumn<Tarefa, String> colunaDescricao;

    @FXML
    private TableColumn<Tarefa, String> colunaStatus;

    private ObservableList<Tarefa> listaTarefas;

    @FXML
    public void initialize() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        listaTarefas = FXCollections.observableArrayList(
            new Tarefa(1, "Tarefa 1", "Pendente"),
            new Tarefa(2, "Tarefa 2", "Concluída")
        );

        tabelaTarefas.setItems(listaTarefas);
    }

    public static class Tarefa {
        private final Integer id;
        private final String descricao;
        private final String status;

        public Tarefa(Integer id, String descricao, String status) {
            this.id = id;
            this.descricao = descricao;
            this.status = status;
        }

        public Integer getId() {
            return id;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getStatus() {
            return status;
        }
    }
}
