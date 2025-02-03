package equipa3.grupo3.GUI.Scenes;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import equipa3.grupo3.services.ApiService;
import equipa3.grupo3.models.TarefaDTO; // Update this to the correct package path

import java.net.URL;
import java.util.ResourceBundle;

public class TabelaTarefas implements Initializable {

    @FXML
    private AnchorPane anchorpane_servico;

    @FXML
    private Button button_adicionartarefa;

    @FXML
    private Button button_concluirServico;

    @FXML
    private Button button_voltarPraTabela;

    @FXML
    private Button button_voltarl;

    @FXML
    private TableView<TarefaDTO> table_servicos;

    @FXML
    private TableColumn<TarefaDTO, String> tc_tipo;

    @FXML
    private TableColumn<TarefaDTO, String> tc_descricaotarefa;

    @FXML
    private TableColumn<TarefaDTO, String> tc_prioridade;

    @FXML
    private TableColumn<TarefaDTO, String> tc_estado;

    @FXML
    private TableColumn<TarefaDTO, String> tc_data;

    @FXML
    private TextField tf_descServico;

    @FXML
    private TextField tf_valorHoraServico;

    @FXML
    private TextField tf_tipoServico;

    private ObservableList<TarefaDTO> listaTarefas = FXCollections.observableArrayList();
    private ApiService apiService = new ApiService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tc_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tc_descricaotarefa.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tc_prioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
        tc_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tc_data.setCellValueFactory(new PropertyValueFactory<>("data"));

        carregarTarefas();

        button_adicionartarefa.setOnAction(ae -> anchorpane_servico.setVisible(true));

        button_concluirServico.setOnAction(ae -> adicionarTarefa());

        button_voltarPraTabela.setOnAction(ae -> anchorpane_servico.setVisible(false));

        button_voltarl.setOnAction(ae -> ScenesController.changeScene("/equipa3/grupo3/GUI/Fxmls/menu.fxml"));
    }

    private void carregarTarefas() {
        try {
            String response = apiService.getData("/tarefas");
            JSONArray tarefasArray = new JSONArray(response);
            for (int i = 0; i < tarefasArray.length(); i++) {
                JSONObject obj = tarefasArray.getJSONObject(i);
                TarefaDTO tarefa = new TarefaDTO(
                    obj.getString("tipo"),
                    obj.getString("descricao"),
                    obj.getString("prioridade"),
                    obj.getString("estado"),
                    obj.getString("data")
                );
                listaTarefas.add(tarefa);
            }
            table_servicos.setItems(listaTarefas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarTarefa() {
        try {
            TarefaDTO novaTarefa = new TarefaDTO(
                tf_tipoServico.getText(),
                tf_descServico.getText(),
                "Média", // Pode ser ajustado conforme necessário
                "Pendente",
                "Hoje" // Aqui pode ser a data atual
            );

            listaTarefas.add(novaTarefa);
            apiService.postData("/tarefas", new JSONObject(novaTarefa).toString());

            tf_descServico.clear();
            tf_tipoServico.clear();
            anchorpane_servico.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
