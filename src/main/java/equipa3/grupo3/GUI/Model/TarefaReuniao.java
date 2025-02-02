package equipa3.grupo3.GUI.Model;

import java.time.LocalDate;

public class TarefaReuniao extends Tarefa {
    private String localReuniao; // Local da reunião
    private String participantes; // Lista de participantes

    // Getters e Setters
    public String getLocalReuniao() {
        return localReuniao;
    }

    public void setLocalReuniao(String localReuniao) {
        this.localReuniao = localReuniao;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    // Construtor
    public TarefaReuniao(int id, String titulo, String descricao, LocalDate data, Prioridade prioridade, Estado estado, Utilizador utilizador, String localReuniao, String participantes) {
        super(id, titulo, descricao, data, prioridade, estado, utilizador);
        this.localReuniao = localReuniao;
        this.participantes = participantes;
    }

    public TarefaReuniao() {
    }
}
