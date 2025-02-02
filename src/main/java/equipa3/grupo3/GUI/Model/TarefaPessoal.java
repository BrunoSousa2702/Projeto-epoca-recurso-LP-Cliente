package equipa3.grupo3.GUI.Model;
import java.time.LocalDate;

public class TarefaPessoal extends Tarefa {
    private boolean lembreteAtivo; // Se há um lembrete ativado para essa tarefa

    // Getters e Setters
    public boolean isLembreteAtivo() {
        return lembreteAtivo;
    }

    public void setLembreteAtivo(boolean lembreteAtivo) {
        this.lembreteAtivo = lembreteAtivo;
    }

    // Construtor
    public TarefaPessoal(int id, String titulo, String descricao, LocalDate data, Prioridade prioridade, Estado estado, Utilizador utilizador, boolean lembreteAtivo) {
        super(id, titulo, descricao, data, prioridade, estado, utilizador);
        this.lembreteAtivo = lembreteAtivo;
    }

    public TarefaPessoal() {
    }
}
