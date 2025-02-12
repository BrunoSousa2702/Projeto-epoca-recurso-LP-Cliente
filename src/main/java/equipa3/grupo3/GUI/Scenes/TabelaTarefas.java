package equipa3.grupo3.GUI.Scenes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import equipa3.grupo3.GUI.Model.Tarefa;
import equipa3.grupo3.GUI.Model.Tarefa.Estado;
import equipa3.grupo3.GUI.Model.Tarefa.Prioridade;

public class TabelaTarefas   {
    
    private AnchorPane anchorpane_tarefa;

    
    private Button button_adicionarTarefa;

    
    private Button button_concluirTarefa;

    
    private Button button_voltar;

    
    private TableColumn<Tarefa, String> tc_descricao;

    
    private TableColumn<Tarefa, Prioridade> tc_prioridade;

    
    private TableColumn<Tarefa, Estado> tc_estado;

    
    private TableColumn<Tarefa, LocalDate> tc_data;
    
    
    private TableView<Tarefa> table_tarefas;

    
    private TextField tf_descTarefa;

    
    private TextField tf_prioridade;

    
    private TextField tf_estado;
    
    
    private TextField tf_data;

    private ObservableList<Tarefa> listaTarefas = FXCollections.observableArrayList();

    public void initialize() {
        tc_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tc_prioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
        tc_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tc_data.setCellValueFactory(new PropertyValueFactory<>("data"));

        table_tarefas.setItems(listaTarefas);

        button_adicionarTarefa.setOnAction(ae -> anchorpane_tarefa.setVisible(true));

        button_concluirTarefa.setOnAction(ae -> adicionarTarefa());

        button_voltar.setOnAction(ae -> anchorpane_tarefa.setVisible(false));
    }

    private void adicionarTarefa() {
        String descricao = tf_descTarefa.getText().trim();
        String prioridadeStr = tf_prioridade.getText().trim().toUpperCase();
        String estadoStr = tf_estado.getText().trim().toUpperCase();
        String dataStr = tf_data.getText().trim();

        if (descricao.isEmpty() || prioridadeStr.isEmpty() || estadoStr.isEmpty() || dataStr.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos!");
            return;
        }

        try {
            Prioridade prioridade = Prioridade.valueOf(prioridadeStr);
            Estado estado = Estado.valueOf(estadoStr);
            LocalDate data = LocalDate.parse(dataStr);

            Tarefa tarefa = new Tarefa(listaTarefas.size() + 1, "Tarefa", descricao, data, prioridade, estado, null);
            listaTarefas.add(tarefa);

            tf_descTarefa.clear();
            tf_prioridade.clear();
            tf_estado.clear();
            tf_data.clear();
            anchorpane_tarefa.setVisible(false);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar tarefa. Verifique os valores inseridos.");
        }
    }
}
